//Emmett Wainwright
//PrinCAD Project
//February, 2020
//Selection Interface

package csci240.prinCad.control;

import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public abstract class CadTool{
	
	protected GraphicsContext _gc;
	
	//Mouse movement properties
	protected boolean _activeMouse = false;
	protected double _xPivot, _yPivot, _xEnd, _yEnd;
	
	public void onMousePressed(CanvasToolInterface canvas, MouseEvent e) {
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
	}
	
	public abstract void onMouseDragged(CanvasToolInterface canvas, MouseEvent e);
	
	public void onMouseRelease(CanvasToolInterface canvas, MouseEvent e) {
		if (_activeMouse) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
			_gc = null;
			
			canvas.reset();
		}
	}

	
	
}
