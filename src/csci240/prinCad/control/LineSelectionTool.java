//Emmett Wainwright
//PrinCAD Project
//February, 2020
//LineSelection

package csci240.prinCad.control;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class LineSelectionTool extends SelectionTool{
	
	@Override
	public void onMouseDragged(CanvasToolInterface canvas, MouseEvent e) {
		if (_activeMouse) {
			_xEnd = e.getX();
			_yEnd = e.getY();
			/*
			if(_xPivot < _xEnd) {
				_xMin = _xPivot;
				_xMax = _xEnd;
			}
			else {
				_xMax = _xPivot;
				_xMin = _xEnd;
			}
			if(_yPivot < _yEnd) {
				_yMin = _yPivot;
				_yMax = _yEnd;
			}
			else {
				_yMax = _yPivot;
				_yMin = _yEnd;
			}
			*/
			_gc.setStroke(Color.ORANGERED);
			_gc.setLineWidth(0);
			canvas.reset();
			//Draw Line
			_gc.strokeLine(_xPivot, _yPivot, _xEnd, _yEnd); //draw line from start to finish
			//_gc.strokeLine(_xMin, _yMin, _xMax, _yMax); //draw line from start to finish
			//Check Selection
			canvas.updateLineSelection(_xPivot, _yPivot, _xEnd, _yEnd);
			//canvas.updateLineSelection(_xMin, _yMin, _xMax, _yMax);
		}
	}
	
}
