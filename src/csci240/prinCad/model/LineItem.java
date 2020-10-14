package csci240.prinCad.model;

import csci240.prinCad.util.Log;
import javafx.scene.canvas.GraphicsContext;

public class LineItem extends CadItem {
	
	private double _x1, _y1, _x2, _y2;
	
	public LineItem(double x1, double y1, double x2, double y2) {
		_x1 = x1;
		_y1 = y1;
		_x2 = x2;
		_y2 = y2;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeLine(_x1, _y1, _x2, _y2);
	}

	@Override
	public String save() {
		return String.format("%1$f, %2$f, %3$f, %4$f", _x1, _y1, _x2, _y2);
	}
	
	@Override
	public CadItem copy() {
		return new LineItem(_x1, _y1, _x2, _y2);
	}
	
	
	@Override
	public void checkLineSelection(double minSelX, double minSelY, double maxSelX, double maxSelY) {
		//Check the selection line to see if it intersects with the shape line
		_isSelected = checkIntersection(minSelX, maxSelX, minSelY, maxSelY, _x1, _x2, _y1, _y2);
		Log.info("Line _isSelected value: " + _isSelected);
	}

	@Override
	public void checkRectangleSelection(double minSelX, double minSelY, double maxSelX, double maxSelY) {
		Log.info("Selection: " + minSelX + " " + minSelY + " " + maxSelX + " " + maxSelY);
		Log.info("Item: " + _x1 + " " + _y1 + " " + _x2 + " " + _y2);
		
		_isSelected = false;
		//If the minimum selection is less than the shape minimum and the maximum selection is greater than the shape max, set selection
		if(minSelX <= _x1 && minSelY <= _y1 && maxSelX >= _x2 && maxSelY >= _y2) {
			_isSelected = true;
		}
		//If selection bound fall outside of the bounds of the shape, set selection to false
		else if(minSelX > _x2 || minSelY > _y2 || maxSelX < _x1 || maxSelY < _y1) {
			_isSelected = false;
		}
		//Otherwise test the hard way
		else { 
			//Check each line of the rectangle to see if it intersects with the shape
			boolean check1 = checkIntersection(minSelX, minSelY, maxSelX, minSelY, _x1, _y1, _x2, _y2);
			boolean check2 = checkIntersection(maxSelX, minSelY, maxSelX, maxSelY, _x1, _y1, _x2, _y2);
			boolean check3 = checkIntersection(minSelX, minSelY, minSelX, maxSelY, _x1, _y1, _x2, _y2);
			boolean check4 = checkIntersection(minSelX, maxSelY, maxSelX, maxSelY, _x1, _y1, _x2, _y2);
			_isSelected = check1 || check2 || check3 || check4; //if any line intersects select item
			Log.info("Check 1: " + check1);
			Log.info("Check 2: " + check2);
			Log.info("Check 3: " + check3);
			Log.info("Check 4: " + check4);
		}
		Log.info("Line _isSelected value: " + _isSelected);
		
	}
	

}
