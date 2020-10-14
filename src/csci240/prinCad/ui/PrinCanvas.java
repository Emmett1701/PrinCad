//Emmett Wainwright
//PrinCAD Project
//February, 2020
//PrinCanvas

package csci240.prinCad.ui;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import csci240.prinCad.command.CanvasCommandInterface;
import csci240.prinCad.control.CadTool;
import csci240.prinCad.control.CanvasToolInterface;
import csci240.prinCad.control.LineSelectionTool;
import csci240.prinCad.control.PolylineTool;
import csci240.prinCad.control.RectangularSelectionTool;
import csci240.prinCad.model.CadItem;
import csci240.prinCad.model.ModelManager;
import csci240.prinCad.model.PointItem;
import csci240.prinCad.util.Log;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PrinCanvas extends Canvas implements CanvasToolInterface, CanvasCommandInterface{
	
	final Color _normalLineColor = SettingsInterface.defaultLineColor;
	final Color _selectionLineColor = SettingsInterface.defaultSelectionColor;
	
	//Model Manager
	private ModelManager _model;
	
	//Graphics context reference
	private GraphicsContext _gc;
	
	//Current selection tool
	private CadTool _selectionTool = new RectangularSelectionTool();
	
	//Tool that is currently active
	private CadTool _activeTool;
	
	public PrinCanvas(double width, double height, Color color, int undoBufferSize) {
		// invoke (call) with parent class constructor
		super(width, height);
		
		//Initialize ModelManager
		_model = new ModelManager(undoBufferSize);
		
		// Get graphics context and fill with background color
		_gc = this.getGraphicsContext2D();
		_gc.setLineWidth(0);
		_gc.setStroke(_normalLineColor);
		_gc.setFill(color);
		_gc.fillRect(0, 0, width, height);
		
		//Subscribe to mouse events
		setOnMousePressed(e -> onMousePressed(e));
		setOnMouseDragged(e -> onMouseDragged(e));
		setOnMouseMoved(e -> onMouseMoved(e));
		setOnMouseReleased(e -> onMouseRelease(e));
		
		_activeTool = _selectionTool;
	}
	
	public PrinCanvas() {
		this(300, 200, SettingsInterface.defaultCanvasBackgroundColor, SettingsInterface.defaultUndoBufferSize);
	}
	
	//Toggles selection type
	public void toggleSelection() {
		if (_selectionTool.getClass() == LineSelectionTool.class) {
			setRectangleSelection();
		}
		else {
			setLineSelection();
		}
		_activeTool = _selectionTool;
	}
	
	public void setLineSelection() {
		_selectionTool = new LineSelectionTool();
		Log.info("Set line selection");
	}
	public void setRectangleSelection() {
		_selectionTool = new RectangularSelectionTool();
		Log.info("Set rectanglular selection");
	}
	
	public void updateLineSelection(double minSelX, double minSelY, double maxSelX, double maxSelY) {
		_model.updateLineSelection(_gc, minSelX, minSelY, maxSelX, maxSelY);
	}
	
	public void updateRectangleSelection(double minSelX, double minSelY, double maxSelX, double maxSelY) {
		_model.updateRectangleSelection(_gc, minSelX, minSelY, maxSelX, maxSelY);
	}
	
	//Draw all graphics objects
	public void draw() {
		_gc.fillRect(0, 0,  getWidth(), getHeight());
		_gc.setStroke(_normalLineColor);
		_gc.setLineWidth(1);
		_model.draw(_gc, _normalLineColor, _selectionLineColor);
	}
	
	//Open model from file
	public void openFromFile(BufferedReader in) {
		_model.load(in);
	}
	
	//Save model to file
	public void saveToFile(PrintWriter out) {
		_model.save(out);
	}
	
	public boolean isBlank() {
		return _model.isEmpty();
	}
	
	//Clear model
	public void clearModel() {
		_gc.fillRect(0, 0,  getWidth(), getHeight()); //clear screen
		_model.clear(); //Tell ModelManager to erase the arraylist contents (the objects)
	}
	
	//Delete Selected Lines
	public void deleteSelected() {
		_model.deleteSelected();
		draw();
	}
	
	//Undo Last saved action
	public void undo() {
		_model.undo();
		draw();
	}
	
	//Redo undone action
	public void redo() {
		_model.redo();
		draw();
	}
	
	//Reset Active tool back to selection mode
	public void reset() {
		draw();
		_activeTool = _selectionTool;
	}
	
	//Add drawn item to the current model
	public void reset(CadItem cadItem) {
		_model.add(cadItem);
		reset(); //includes functionality of parameterless reset
	}
	
	public void reset(ArrayList<PointItem> pointItem) {
		_model.add(pointItem);
		reset(); //includes functionality of parameterless reset
	}
	
	//Set Active Tool
	public void setActiveTool(CadTool activeTool) {
		_activeTool = activeTool;
	}
	
	// Handle mouse pressed (button down)
	private void onMousePressed(MouseEvent e) {
		_activeTool.onMousePressed(this, e);
	}
	
	// Handle mouse drag (only called when mouse button IS depressed)
	private void onMouseDragged(MouseEvent e) {
		_activeTool.onMouseDragged(this, e);
	}
	
	//Handle mouse move
	private void onMouseMoved(MouseEvent e) {
		if(_activeTool instanceof PolylineTool) {
			_activeTool.onMouseDragged(this, e);
		}
	}
	
	// Handle mouse release (button up)
	private void onMouseRelease(MouseEvent e) {
		_activeTool.onMouseRelease(this, e);
	}

}
