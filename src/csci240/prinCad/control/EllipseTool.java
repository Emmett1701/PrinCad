//Emmett Wainwright
//PrinCAD Project
//March, 2020
//EllipseTool

package csci240.prinCad.control;

import csci240.prinCad.model.EllipseItem;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

public class EllipseTool extends CadTool {
	
	private double _xRadius;
	private double _yRadius;
	
	@Override
	public void onMouseDragged(CanvasToolInterface canvas, MouseEvent e) {
		//finds absolute value of width and height of distance from pivot
		_xRadius = Math.abs(_xEnd - _xPivot);
		_yRadius = Math.abs(_yEnd - _yPivot);
		canvas.draw();
		_xEnd = e.getX();
		_yEnd = e.getY();
		//draw circle
		_gc.strokeOval(_xPivot-_xRadius, _yPivot-_yRadius, _xRadius*2, _yRadius*2); //Draw Circle
	}
	
	@Override
	public void onMouseRelease(CanvasToolInterface canvas, MouseEvent e) {
		if (_activeMouse) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
			_gc = null;
			
			canvas.reset(new EllipseItem(_xPivot, _yPivot, _xRadius, _yRadius)); //Save Item and reset to selection
		}
	}

}
