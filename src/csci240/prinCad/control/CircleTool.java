package csci240.prinCad.control;

import csci240.prinCad.model.CircleItem;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;

public class CircleTool extends CadTool {
	
	private double _radius;
	
	@Override
	public void onMouseDragged(CanvasToolInterface canvas, MouseEvent e) {
		//finds absolute value of width and height of distance from pivot
		double w = Math.abs(_xEnd - _xPivot);
		double h = Math.abs(_yEnd - _yPivot);
		_radius = Math.sqrt(w*w + h*h); //circle radius
		canvas.draw();
		_xEnd = e.getX();
		_yEnd = e.getY();
		//draw circle
		_gc.strokeOval(_xPivot-_radius, _yPivot-_radius, _radius*2, _radius*2); //Draw Circle
	}
	
	@Override
	public void onMouseRelease(CanvasToolInterface canvas, MouseEvent e) {
		if (_activeMouse) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
			_gc = null;
			
			canvas.reset(new CircleItem(_xPivot, _yPivot, _radius)); //Save Item and reset to selection
		}
	}

}
