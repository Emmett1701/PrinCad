//Emmett Wainwright
//PrinCAD Project
//February, 2020
//RectangularSelection

package csci240.prinCad.control;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class RectangularSelectionTool extends SelectionTool{
	
	@Override
	public void onMouseDragged(CanvasToolInterface canvas, MouseEvent e) {
		if (_activeMouse) {
			_xEnd = e.getX();
			_yEnd = e.getY();
			_gc.setStroke(Color.ORANGERED);
			_gc.setLineWidth(0);
			canvas.reset();
			//draw lines around edges of rectangle
			_gc.strokeLine(_xPivot, _yPivot, _xEnd, _yPivot); //Top left to top right
			_gc.strokeLine(_xEnd, _yPivot, _xEnd, _yEnd); //Top right to bottom right
			_gc.strokeLine(_xPivot, _yPivot, _xPivot, _yEnd); //Top left to bottom left
			_gc.strokeLine(_xPivot, _yEnd, _xEnd, _yEnd); //Bottom left to bottom right
			//Call Selection
			canvas.updateRectangleSelection(_xPivot, _yPivot, _xEnd, _yEnd);
		}
	}
	
	
	
}
