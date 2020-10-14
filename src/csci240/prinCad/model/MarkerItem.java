package csci240.prinCad.model;

public abstract class MarkerItem extends CadItem {
	
	protected double _x, _y;
	
	public MarkerItem(double x, double y) {
		_x = x;
		_y = y;
	}
	
	@Override
	public String save() {
		return String.format("%1$f, %2$f", _x, _y);
	}
	
	public void checkLineSelection(double minSelX, double maxSelX, double minSelY, double maxSelY) {
		//TODO:
	}
	
	public void checkRectangleSelection(double minSelX, double maxSelX, double minSelY, double maxSelY) {
		if(minSelX < _x && maxSelX > _x && minSelY < _y && maxSelY > _y) {
			_isSelected = true;
		}
		else {
			_isSelected = false;
		}
	}

}
