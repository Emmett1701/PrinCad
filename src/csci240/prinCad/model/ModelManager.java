//Emmett Wainwright
//PrinCAD Project
//ModelManager

package csci240.prinCad.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import javax.swing.JFileChooser;

import csci240.prinCad.util.Log;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ModelManager {
	
	final JFileChooser fc = new JFileChooser();
	
	private ArrayList<CadItem> _list;
	private Deque<ModelData> _undoQueue;
	private Deque<ModelData> _redoQueue;
	
	final private int _undoSize;// Size of undo buffer
	
	// Constructor
	public ModelManager(int undoSize) {
		_undoSize = undoSize;
		_list = new ArrayList<CadItem>();
		_undoQueue = new ArrayDeque<ModelData>(_undoSize);
		_redoQueue = new ArrayDeque<ModelData>(_undoSize);
		Log.info("Model Manager Initialized");
	}
	
	//Add a new cadItem to the list of cadItems
	public void add(CadItem cadItem) {
		saveState();
		_list.add(cadItem);
		Log.info(cadItem.getClass() + " created.");
	}
	
	//Add a new polyline Item to the list of cadItems
	public void add(ArrayList<PointItem> cadItem) {
		saveState();
		_list.add(new PolylineItem(cadItem));
		Log.info(PolylineItem.class + " created.");
	}
	
	//Method to draw the current list of caditems to the screen
	public void draw(GraphicsContext gc, Color normalColor, Color selectionColor) {
		for(int i=0; i<_list.size(); i++) {
			_list.get(i).draw(gc, normalColor, selectionColor);
		}
	}
	
	//Method to call saveModelState and clear the redo queue
	//Called when edit is made
	private void saveState() {
		saveModelData();
		_redoQueue.clear();
		Log.info("Current model state saved");
	}
	
	//Method to save the current model state to the undoQueue
	private void saveModelData() {
		//If undoQueue is full pop off oldest item
		if (_undoQueue.size() == _undoSize) {
			_undoQueue.removeLast();
		}
		_undoQueue.push(new ModelData(_list));
	}
	
	//Method to undo last action
	public void undo() {
		if(!_undoQueue.isEmpty()) {
			_redoQueue.push(new ModelData(_list)); //Push current state to redo queue
			_list = _undoQueue.pop().getItems(); //Pop top of undo queue to current state
			Log.info("Undo - Returned back one action");
		}
		else {
			Log.error("Undo - Cannot undo action");
		}
	}
	
	//Method to redo last undo
	public void redo() {
		if(!_redoQueue.isEmpty()) {
			_undoQueue.push(new ModelData(_list)); //Push current state to undo queue
			_list = _redoQueue.pop().getItems(); //Pop top of redo queue to the current state
			Log.info("Redo - Returned forward one action");
		}else {
			Log.error("Redo - Cannot undo action");
		}
	}
	
	public void updateLineSelection(GraphicsContext gc, double minSelX, double minSelY, double maxSelX, double maxSelY) {
		
		for(int i=0; i < _list.size(); i++) {
			_list.get(i).checkLineSelection(minSelX, minSelY, maxSelX, maxSelY);
		}
	}
	
	public void updateRectangleSelection(GraphicsContext gc, double minSelX, double minSelY, double maxSelX, double maxSelY) {
		for(int i=0; i < _list.size(); i++) {
			_list.get(i).checkRectangleSelection(minSelX, minSelY, maxSelX, maxSelY);
		}
	}
	
	public boolean isEmpty() {
		return _list.isEmpty();
	}
	
	public void clear() {
		_list.clear();
		Log.info("ArrayList of cad items cleared");
	}
	
	public void deleteSelected() {
		int i=0;
		while(i < _list.size()) {
			if(_list.get(i).isSelected())
				_list.remove(i);
			else
				i++;
		}
		Log.info("Selected items deleted");
	}
	
	public void load(BufferedReader in) {
		Log.info("Reading file using ModelManager.load method.");
		try {
			String line = in.readLine();
			String classString = "";
			while(line != null) {
				//get object type for each line
				classString = line.substring(line.indexOf("<") + 7, line.indexOf(">"));
				Class<?> classType = Class.forName(classString);
				//chop off ending of line
				line = line.substring(line.indexOf(">") + 1, line.indexOf("</"));
				
				if(classType.equals(LineItem.class)) {
					double x1 = Double.valueOf(line.substring(0,line.indexOf(",")-1));
					line = line.substring(line.indexOf(",") + 1);
					double y1 = Double.valueOf(line.substring(0,line.indexOf(",")-1));
					line = line.substring(line.indexOf(",") + 1);
					double x2 = Double.valueOf(line.substring(0,line.indexOf(",")-1));
					line = line.substring(line.indexOf(",") + 1);
					double y2 = Double.valueOf(line.substring(0));
					_list.add(new LineItem(x1, y1, x2, y2));
				}
				else if(classType.equals(RectangleItem.class)) {
					double x1 = Double.valueOf(line.substring(0,line.indexOf(",")-1));
					line = line.substring(line.indexOf(",") + 1);
					double y1 = Double.valueOf(line.substring(0,line.indexOf(",")-1));
					line = line.substring(line.indexOf(",") + 1);
					double x2 = Double.valueOf(line.substring(0,line.indexOf(",")-1));
					line = line.substring(line.indexOf(",") + 1);
					double y2 = Double.valueOf(line.substring(0));
					_list.add(new RectangleItem(x1, y1, x2, y2));
				}
				else if(classType.equals(CircleItem.class)) {
					double x1 = Double.valueOf(line.substring(0,line.indexOf(",")-1));
					line = line.substring(line.indexOf(",") + 1);
					double y1 = Double.valueOf(line.substring(0,line.indexOf(",")-1));
					line = line.substring(line.indexOf(",") + 1);
					double r = Double.valueOf(line.substring(0));
					_list.add(new CircleItem(x1, y1, r));
				}
				else if(classType.equals(EllipseItem.class)) { 
					double x1 = Double.valueOf(line.substring(0,line.indexOf(",")-1));
					line = line.substring(line.indexOf(",") + 1);
					double y1 = Double.valueOf(line.substring(0,line.indexOf(",")-1));
					line = line.substring(line.indexOf(",") + 1);
					double xR = Double.valueOf(line.substring(0,line.indexOf(",")-1));
					line = line.substring(line.indexOf(",") + 1);
					double yR = Double.valueOf(line.substring(0));
					_list.add(new EllipseItem(x1, y1, xR, yR));
				}
				else if(classType.equals(PolylineItem.class)) {
					ArrayList<PointItem> polyline = new ArrayList<PointItem>();
					double x, y;
					while(!line.isEmpty()) {
						x = Double.valueOf(line.substring(1,line.indexOf(",")-1));
						line = line.substring(line.indexOf(",") + 1);
						y = Double.valueOf(line.substring(1,line.indexOf(")")-1));
						line = line.substring(line.indexOf(")") + 1);
						polyline.add(new PointItem(x,y));
					}
					_list.add(new PolylineItem(polyline));
				}
				else if(classType.equals(null)) {
					Log.error("Read in a null value for class type from save file.");
				}
				else {
					Log.error("Unknown class of type " + classType + " read in from save file");
				}
				line = in.readLine();
			}
		}
		catch (IOException ex) {
			Log.error("Error reading from save file with exception ", ex);
		}
		catch (ClassNotFoundException ex) {
			Log.error("Error reading class data from save file with exception ", ex);
		}
	}

	public void save(PrintWriter out) {
		
		try {
			
			for(int i=0; i<_list.size(); i++) {
				out.println("<" + _list.get(i).getClass() + ">" + _list.get(i).save() + "</" +  _list.get(i).getClass() + ">");
			}
			
			out.flush();
			out.close();
		}
		catch(Exception e) {
			Log.error("Error saving to file with exception ", e);
		}
		
	}
	
}
