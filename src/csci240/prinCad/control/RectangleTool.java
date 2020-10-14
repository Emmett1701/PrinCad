package csci240.prinCad.control;

import csci240.prinCad.model.RectangleItem;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;


public class RectangleTool extends CadTool {
	
	@Override
	public void onMouseDragged(CanvasToolInterface canvas, MouseEvent e) {
		if (_activeMouse) {
			canvas.draw();
			_xEnd = e.getX();
			_yEnd = e.getY();
			//draw lines around edges of rectangle
			_gc.strokeLine(_xPivot, _yPivot, _xEnd, _yPivot);
			_gc.strokeLine(_xEnd, _yPivot, _xEnd, _yEnd);
			_gc.strokeLine(_xPivot, _yPivot, _xPivot, _yEnd);
			_gc.strokeLine(_xPivot, _yEnd, _xEnd, _yEnd);
		}
	}
	
	@Override
	public void onMouseRelease(CanvasToolInterface canvas, MouseEvent e) {
		if (_activeMouse) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
			_gc = null;
			
			canvas.reset(new RectangleItem(_xPivot, _yPivot, _xEnd, _yEnd)); //Save Item and reset to selection
		}
	}

}
