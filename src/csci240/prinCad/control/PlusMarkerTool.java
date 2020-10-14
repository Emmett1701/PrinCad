package csci240.prinCad.control;

import csci240.prinCad.model.PlusMarkerItem;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PlusMarkerTool extends MarkerTool {

	@Override
	protected void Draw(CanvasToolInterface canvas, MouseEvent e) {
		_xPivot = e.getX();
		_yPivot = e.getY();
		
		canvas.getGraphicsContext2D().strokeLine(_xPivot - MarkerSize, _yPivot, _xPivot + MarkerSize, _yPivot);
		canvas.getGraphicsContext2D().strokeLine(_xPivot, _yPivot - MarkerSize, _xPivot, _yPivot + MarkerSize);
	}
	
	@Override //MarkerTool is different from super class onMouseReleased Method
	public void onMouseRelease(CanvasToolInterface canvas, MouseEvent e) {
		//Place marker upon button release
		if (_activeMouse) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
			canvas.getGraphicsContext2D().setStroke(Color.ORANGERED);
			canvas.getGraphicsContext2D().setLineWidth(0);
			
			Draw(canvas, e);
			canvas.reset(new PlusMarkerItem(_xPivot, _yPivot));
		}
	}

}
