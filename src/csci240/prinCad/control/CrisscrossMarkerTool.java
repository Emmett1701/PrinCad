package csci240.prinCad.control;

import csci240.prinCad.model.CrisscrossMarkerItem;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CrisscrossMarkerTool extends MarkerTool {

	@Override
	protected void Draw(CanvasToolInterface canvas, MouseEvent e) {
		_xPivot = e.getX();
		_yPivot = e.getY();
		
		canvas.getGraphicsContext2D().strokeLine(_xPivot - MarkerSize, _yPivot - MarkerSize, _xPivot + MarkerSize, _yPivot + MarkerSize); //Top left to bottom right
		canvas.getGraphicsContext2D().strokeLine(_xPivot - MarkerSize, _yPivot + MarkerSize, _xPivot + MarkerSize, _yPivot - MarkerSize); //Bottom left to top right
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
			canvas.reset(new CrisscrossMarkerItem(_xPivot, _yPivot));
		}
	}

}
