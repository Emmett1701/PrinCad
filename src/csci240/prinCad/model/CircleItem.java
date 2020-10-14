package csci240.prinCad.model;

import javafx.scene.canvas.GraphicsContext;

public class CircleItem extends CadItem {

	double _x, _y, _r;
	
	public CircleItem(double x, double y, double r) {
		_x = x;
		_y = y;
		_r = r;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeOval(_x-_r, _y-_r, _r*2, _r*2);
	}

	@Override
	public String save() {
		return String.format("%1$f, %2$f, %3$f", _x, _y, _r);
	}
	
	@Override
	public CadItem copy() {
		return new CircleItem(_x, _y, _r);
	}
	

	@Override
	public void checkLineSelection(double minSelX, double maxSelX, double minSelY, double maxSelY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkRectangleSelection(double minSelX, double maxSelX, double minSelY, double maxSelY) {
		
		_isSelected = false;
		//If the minimum selection is less than the shape minimum and the maximum selection is greater than the shape max, set selection
		if(minSelX <= _x - _r && minSelY <= _y - _r && maxSelX >= _x + _r && maxSelY >= _y + _r) {
			_isSelected = true;
		}
		//If selection bound fall outside of the bounds of the shape, set selection to false
		else if(minSelX > _x - _r || minSelY > _y - _r || maxSelX < _x + _r || maxSelY < _y + _r) {
			_isSelected = false;
		}
	}
	
	
}
