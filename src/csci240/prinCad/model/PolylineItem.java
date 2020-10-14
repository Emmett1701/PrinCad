package csci240.prinCad.model;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public class PolylineItem extends CadItem {
	
	ArrayList<PointItem> _polyline = new ArrayList<PointItem>();
	
	public PolylineItem(ArrayList<PointItem> polyline) {
		_polyline = polyline;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		double x1 = _polyline.get(0).getX();
		double y1 = _polyline.get(0).getY();
		double x2, y2;
		for(int i=1; i<_polyline.size(); i++) {
			x2 = _polyline.get(i).getX();
			y2 = _polyline.get(i).getY();
			gc.strokeLine(x1, y1, x2, y2);
			x1 = x2;
			y1 = y2;
		}
	}

	@Override
	public String save() {
		String stringFormat = "";
		for(int i=0; i<_polyline.size(); i++) {
			stringFormat += String.format("(%1$f, %2$f)", _polyline.get(i).getX(), _polyline.get(i).getY());
		}
		return stringFormat;
	}
	
	@Override
	public CadItem copy() {
		ArrayList<PointItem> polyline = new ArrayList<PointItem>();
		for(PointItem item : _polyline) {
			polyline.add(item.copy());
		}
		return new PolylineItem(polyline);
	}

	@Override
	public void checkLineSelection(double minSelX, double maxSelX, double minSelY, double maxSelY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkRectangleSelection(double minSelX, double maxSelX, double minSelY, double maxSelY) {
		
		_isSelected = false;
		for(int i=1; i<_polyline.size(); i++) {
			//If the minimum selection is less than the shape minimum and the maximum selection is greater than the shape max, set selection
			if(minSelX <= _polyline.get(i-1).getX() && minSelY <= _polyline.get(i-1).getY() && maxSelX >= _polyline.get(i).getX() && maxSelY >= _polyline.get(i).getY()) {
				_isSelected = true;
				return;
			}
		}
	}

}
