package csci240.prinCad.control;

import csci240.prinCad.model.LineItem;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;


public class LineTool extends CadTool {

	@Override
	public void onMouseDragged(CanvasToolInterface canvas, MouseEvent e) {
		canvas.draw();
		_xEnd = e.getX();
		_yEnd = e.getY();
		_gc.strokeLine(_xPivot, _yPivot, _xEnd, _yEnd); //draw line from start to finish
	}
	
	@Override
	public void onMouseRelease(CanvasToolInterface canvas, MouseEvent e) {
		if (_activeMouse) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
			_gc = null;
			
			canvas.reset(new LineItem(_xPivot, _yPivot, _xEnd, _yEnd)); //Save Item and reset to selection
		}
	}

}
