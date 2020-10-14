package csci240.prinCad.model;

import csci240.prinCad.control.MarkerTool;
import javafx.scene.canvas.GraphicsContext;

public class CrisscrossMarkerItem extends MarkerItem {

	public CrisscrossMarkerItem(double x, double y) {
		super(x, y);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeLine(_x - MarkerTool.MarkerSize, _y - MarkerTool.MarkerSize, _x + MarkerTool.MarkerSize, _y + MarkerTool.MarkerSize); //Top left to bottom right
		gc.strokeLine(_x - MarkerTool.MarkerSize, _y + MarkerTool.MarkerSize, _x + MarkerTool.MarkerSize, _y - MarkerTool.MarkerSize); //Bottom left to top right
	}
	
	@Override
	public CadItem copy() {
		return new CrisscrossMarkerItem(_x, _y);
	}

}
