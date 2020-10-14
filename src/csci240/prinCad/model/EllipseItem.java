package csci240.prinCad.model;

import javafx.scene.canvas.GraphicsContext;

public class EllipseItem extends CadItem {

	double _x, _y, _rX, _rY;
	
	public EllipseItem(double x, double y, double rX, double rY) {
		_x = x;
		_y = y;
		_rX = rX;
		_rY = rY;
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeOval(_x-_rX, _y-_rY, _rX*2, _rY*2);
	}

	@Override
	public String save() {
		return String.format("%1$f, %2$f, %3$f, %4$f", _x, _y, _rX, _rY);
	}
	
	@Override
	public CadItem copy() {
		return new EllipseItem(_x, _y, _rX, _rY);
	}

	@Override
	public void checkLineSelection(double minSelX, double maxSelX, double minSelY, double maxSelY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkRectangleSelection(double minSelX, double maxSelX, double minSelY, double maxSelY) {
		
		_isSelected = false;
		//If the minimum selection is less than the shape minimum and the maximum selection is greater than the shape max, set selection
		if(minSelX <= _x - _rX && minSelY <= _y - _rY && maxSelX >= _x + _rX && maxSelY >= _y + _rY) {
			_isSelected = true;
		}
		//If selection bound fall outside of the bounds of the shape, set selection to false
		else if(minSelX > _x - _rX || minSelY > _y - _rY || maxSelX < _x + _rX || maxSelY < _y + _rY) {
			_isSelected = false;
		}
	}

}
