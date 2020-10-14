package csci240.prinCad.model;

import javafx.scene.canvas.GraphicsContext;

public class RectangleItem extends CadItem {
	
	double _x1, _y1, _x2, _y2;
	
	public RectangleItem(double x1, double y1, double x2, double y2) {
		_x1 = x1;
		_y1 = y1;
		_x2 = x2;
		_y2 = y2;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeLine(_x1, _y1, _x2, _y1);
		gc.strokeLine(_x2, _y1, _x2, _y2);
		gc.strokeLine(_x1, _y1, _x1, _y2);
		gc.strokeLine(_x1, _y2, _x2, _y2);
	}

	@Override
	public String save() {
		return String.format("%1$f, %2$f, %3$f, %4$f", _x1, _y1, _x2, _y2);
	}
	
	@Override
	public CadItem copy() {
		return new RectangleItem(_x1, _y1, _x2, _y2);
	}

	@Override
	public void checkLineSelection(double minSelX, double maxSelX, double minSelY, double maxSelY) {
		//Check each line of the rectangle to see if it intersects with the shape
		boolean check1 = checkIntersection(minSelX, maxSelX, minSelY, minSelY, _x1, _x2, _y1, _y1);
		boolean check2 = checkIntersection(minSelX, maxSelX, minSelY, minSelY, _x2, _x2, _y1, _y2);
		boolean check3 = checkIntersection(minSelX, maxSelX, minSelY, minSelY, _x1, _x1, _y1, _y2);
		boolean check4 = checkIntersection(minSelX, maxSelX, minSelY, minSelY, _x1, _x2, _y2, _y2);
		_isSelected = check1 || check2 || check3 || check4; //if any line intersects select item
		
	}

	@Override
	public void checkRectangleSelection(double minSelX, double maxSelX, double minSelY, double maxSelY) {
		
		_isSelected = false;
		//If the minimum selection is less than the shape minimum and the maximum selection is greater than the shape max, set selection
		if(minSelX <= _x1 && minSelY <= _y1 && maxSelX >= _x2 && maxSelY >= _y2) {
			_isSelected = true;
		}
		//If selection bound fall outside of the bounds of the shape, set selection to false
		else if(minSelX > _x2 || minSelY > _y2 || maxSelX < _x1 || maxSelY < _y1) {
			_isSelected = false;
		}
	}

}
