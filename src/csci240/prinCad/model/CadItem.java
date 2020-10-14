package csci240.prinCad.model;

import csci240.prinCad.util.Log;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class CadItem {
	
	protected boolean _isSelected = false;
	
	public boolean isSelected() {return _isSelected;}
	
	protected abstract void draw(GraphicsContext gc);
	public abstract String save();
	public abstract void checkLineSelection(double minSelX, double minSelY, double maxSelX, double maxSelY);
	public abstract void checkRectangleSelection(double minSelX, double minSelY, double maxSelX, double maxSelY);
	
	
	public void draw(GraphicsContext gc, Color normalColor, Color selectionColor) {
		if(this._isSelected) {
			gc.setStroke(selectionColor);
			gc.setLineWidth(0);
			draw(gc);
		}
		else {
			gc.setStroke(normalColor);
			gc.setLineWidth(0);
			draw(gc);
		}
	}
	
	public abstract CadItem copy();

	
	protected boolean checkIntersection(double minSelX, double minSelY, double maxSelX, double maxSelY, double minItemX, double minItemY, double maxItemX, double maxItemY) {
		double selSlope = 0;
		double itemSlope = 0;
		double xIntersect = 0;
		double yIntersect = 0;
		
		
		if((maxSelX-minSelX) != 0) { //Check for vertical selection line to prevent division by 0
			selSlope = Math.abs(maxSelY-minSelY)/Math.abs(maxSelX-minSelX);
			
			if(maxItemX - minItemX != 0) { //Check for vertical item line to prevent division by 0
				itemSlope = Math.abs(maxItemY-minItemY)/Math.abs(maxItemX-minItemX);
				
				if(itemSlope - selSlope != 0) { //Check for parallel lines to prevent division by 0
					xIntersect = (minItemY - minSelY - (itemSlope * minItemX) + (selSlope * minSelX)) / (selSlope - itemSlope);
					yIntersect = itemSlope * (xIntersect - minItemX) + minItemY;
					Log.info("1");
				}
				else { //Lines are parallel
					return false; // Lines cannot intersect and therefore cannot select each other
				}
			}
			else { //Item Line is vertical
				yIntersect = maxItemY;
				xIntersect = selSlope * (minItemX - minSelX) + minSelY;
			}
		}
		else if((maxSelY-minSelY) != 0) { //Selection Line is vertical
			yIntersect = minSelY;
			
			if(maxItemX - minItemX != 0) { //Check for vertical item line to prevent division by 0
				itemSlope = (maxItemY-minItemY)/(maxItemX-minItemX);
				xIntersect = (1/itemSlope) * (minSelY - minItemY) + minItemX;
				Log.info("2");
			}
			else { //Lines are parallel
				return false; // Lines cannot intersect and therefore cannot select each other
			}
		}
		else {
			Log.error("Error while finding intersection of " + this.getClass() + " due to invalid selection line with points: ("
					+ minSelX + "," + minSelY + ") and (" + maxSelX + "," + maxSelY + ").");
			return false;
			//TODO: Throw exception
		}
		Log.info("Selection: " + minSelX + " " + minSelY + " " + maxSelX + " " + maxSelY);
		Log.info("Item: " + minItemX + " " + minItemY + " " + maxItemX + " " + maxItemY);
		Log.info("selSlope: " + selSlope + " itemSlope:" + itemSlope);
		Log.info("xIntersect: " + xIntersect + " yIntersect:" + yIntersect);
		if ((xIntersect > minItemX && xIntersect < maxItemX && yIntersect > minItemY && yIntersect < maxItemY && xIntersect > minSelX && xIntersect < maxSelX && yIntersect > minSelY && yIntersect < maxSelY)) {
			return true;
		}
		return false;
	}
}
