//Emmett Wainwright
//PrinCAD Project
//February, 2020
//SelectionTool

package csci240.prinCad.control;

import csci240.prinCad.util.Log;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public abstract class SelectionTool extends CadTool {
	
	double _xMin, _xMax, _yMin, _yMax;
	
	@Override //SelectionTool is different from onMousePressed super method
	public void onMousePressed(CanvasToolInterface canvas, MouseEvent e) {
		if (e.getButton() == MouseButton.PRIMARY) {
			Log.info("Using Selection Tool");
			
			double x = e.getX();
			double y = e.getY();
			_xPivot = x;
			_yPivot = y;
			_xEnd = x;
			_yEnd = y;
			_activeMouse = true;
			_gc = canvas.getGraphicsContext2D();
			canvas.setCursor(Cursor.CROSSHAIR);
			
		}
	}
	
	@Override
	public abstract void onMouseDragged(CanvasToolInterface canvas, MouseEvent e);
	
	@Override //SelectionTool is different from onMouseRelease super method
	public void onMouseRelease(CanvasToolInterface canvas, MouseEvent e) {
		if (_activeMouse) {
			_activeMouse = false;
			canvas.setCursor(Cursor.DEFAULT);
			_gc = null;
			
			canvas.reset();
		}
	}
	
}
