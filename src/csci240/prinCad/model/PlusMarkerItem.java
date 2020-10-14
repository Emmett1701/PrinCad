package csci240.prinCad.model;

import csci240.prinCad.control.MarkerTool;
import javafx.scene.canvas.GraphicsContext;

public class PlusMarkerItem extends MarkerItem {

	public PlusMarkerItem(double x, double y) {
		super(x, y);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeLine(_x - MarkerTool.MarkerSize, _y, _x + MarkerTool.MarkerSize, _y);
		gc.strokeLine(_x, _y - MarkerTool.MarkerSize, _x, _y + MarkerTool.MarkerSize);
	}
	
	@Override
	public CadItem copy() {
		return new PlusMarkerItem(_x, _y);
	}

}
