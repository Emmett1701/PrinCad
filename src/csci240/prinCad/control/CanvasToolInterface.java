package csci240.prinCad.control;

import java.util.ArrayList;

import csci240.prinCad.model.CadItem;
import csci240.prinCad.model.PointItem;
import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;

public interface CanvasToolInterface {
	public GraphicsContext getGraphicsContext2D();
	public void setCursor(Cursor cursor);
	public void draw();
	public void reset();
	public void reset(CadItem cadItem);
	public void reset(ArrayList<PointItem> pointItem);
	public void updateLineSelection(double _xPivot, double _yPivot, double _xEnd, double _yEnd);
	public void updateRectangleSelection(double _xPivot, double _yPivot, double _xEnd, double _yEnd);
	
}
