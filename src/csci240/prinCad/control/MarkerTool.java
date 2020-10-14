package csci240.prinCad.control;

import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public abstract class MarkerTool extends CadTool {
	
	public static final int MarkerSize = 4;
	
	@Override //MarkerTool is different from super class onMousePressed Method
	public void onMousePressed(CanvasToolInterface canvas, MouseEvent e) {
		//Only record primary mouse button clicks for markers
		if (e.getButton() == MouseButton.PRIMARY) {
			canvas.setCursor(Cursor.CROSSHAIR);
			_activeMouse = true;
		}
	}

	@Override
	public void onMouseDragged(CanvasToolInterface canvas, MouseEvent e) { }

	@Override //MarkerTool is different from super class onMouseReleased Method
	public void onMouseRelease(CanvasToolInterface canvas, MouseEvent e) {
		//Place marker upon button release
		if (_activeMouse) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
			canvas.getGraphicsContext2D().setStroke(Color.ORANGERED);
			canvas.getGraphicsContext2D().setLineWidth(0);
			
			Draw(canvas, e);
			canvas.reset();
		}
	}
	
	protected abstract void Draw(CanvasToolInterface canvas, MouseEvent e);

}
