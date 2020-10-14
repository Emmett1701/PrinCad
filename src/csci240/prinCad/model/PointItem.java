package csci240.prinCad.model;

import csci240.prinCad.util.Log;
import javafx.scene.canvas.GraphicsContext;

public class PointItem extends CadItem {
	
	private double _x, _y;
	
	public PointItem(double x, double y) {
		_x = x;
		_y = y;
	}
	
	public double getX() {
		return _x;
	}
	
	public double getY() {
		return _y;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		// Empty Method (Will not draw point items)
		Log.error("Point Item draw method called.");
	}

	@Override
	public String save() {
		return String.format("%1$f, %2$f", _x, _y);
	}
	
	@Override
	public PointItem copy() {
		return new PointItem(_x, _y);
	}

	@Override
	public void checkLineSelection(double minSelX, double maxSelX, double minSelY, double maxSelY) {
		// Empty Method (Will not check selection of points)
		Log.error("Point Item checkLineSelection method called.");
	}

	@Override
	public void checkRectangleSelection(double minSelX, double maxSelX, double minSelY, double maxSelY) {
		// Empty Method (Will not check selection of points)
		Log.error("Point Item checkRectangleSelection method called.");
	}

}
