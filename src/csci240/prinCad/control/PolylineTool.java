package csci240.prinCad.control;

import java.util.ArrayList;

import csci240.prinCad.model.PointItem;
import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PolylineTool extends CadTool {
	
private GraphicsContext _gc;
	
	//Mouse movement properties
	boolean _activeMouse = false;
	double _xPivot, _yPivot, _xEnd, _yEnd;
	
	ArrayList<PointItem> _polyline = new ArrayList<PointItem>();
	
	@Override //Polyline is different from super class onMousePressed Method
	public void onMousePressed(CanvasToolInterface canvas, MouseEvent e) {
		
		//If Primary mouse button activate mouse
		if (e.getButton() == MouseButton.PRIMARY) {
			double x = e.getX();
			double y = e.getY();
			_xPivot = x;
			_yPivot = y;
			_xEnd = x;
			_yEnd = y;
			_activeMouse = true;
			_gc = canvas.getGraphicsContext2D();
			_gc.setStroke(Color.ORANGERED);
			_gc.setLineWidth(0);
			canvas.setCursor(Cursor.CROSSHAIR);
			
			_polyline.add(new PointItem(x, y));
		}
		//If secondary mouse button
		else if (e.getButton() == MouseButton.SECONDARY && _activeMouse == true) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
			_gc = null;
			
			canvas.reset(_polyline); //Save Item and reset to selection
		}
	}

	@Override 
	public void onMouseDragged(CanvasToolInterface canvas, MouseEvent e) {
		if(_activeMouse) {
			canvas.draw();
			double x1 = _polyline.get(0).getX();
			double y1 = _polyline.get(0).getY();
			double x2, y2;
			for(int i=1; i<_polyline.size(); i++) {
				x2 = _polyline.get(i).getX();
				y2 = _polyline.get(i).getY();
				_gc.strokeLine(x1, y1, x2, y2);
				x1 = x2;
				y1 = y2;
			}
			_xEnd = e.getX();
			_yEnd = e.getY();
			_gc.strokeLine(_xPivot, _yPivot, _xEnd, _yEnd); //draw line from start to finish
		}
	}

	@Override //Polyline is different from super class onMouseReleased Method
	public void onMouseRelease(CanvasToolInterface canvas, MouseEvent e) {
		//Do nothing
	}

}
