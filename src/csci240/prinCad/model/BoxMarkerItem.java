package csci240.prinCad.model;

import csci240.prinCad.control.MarkerTool;
import javafx.scene.canvas.GraphicsContext;

public class BoxMarkerItem extends MarkerItem {

	public BoxMarkerItem(double x, double y) {
		super(x, y);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeLine(_x - MarkerTool.MarkerSize, _y - MarkerTool.MarkerSize, _x + MarkerTool.MarkerSize, _y - MarkerTool.MarkerSize); //top left to top right
		gc.strokeLine(_x - MarkerTool.MarkerSize, _y + MarkerTool.MarkerSize, _x + MarkerTool.MarkerSize, _y + MarkerTool.MarkerSize); //bottom left to bottom right
		gc.strokeLine(_x - MarkerTool.MarkerSize, _y - MarkerTool.MarkerSize, _x - MarkerTool.MarkerSize, _y + MarkerTool.MarkerSize); //top left to bottom left
		gc.strokeLine(_x + MarkerTool.MarkerSize, _y - MarkerTool.MarkerSize, _x + MarkerTool.MarkerSize, _y + MarkerTool.MarkerSize); //top right to bottom right
	}
	
	@Override
	public CadItem copy() {
		return new BoxMarkerItem(_x, _y);
	}

}
