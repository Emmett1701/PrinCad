//Emmett Wainwright
//PrinCAD Project
//ModelData

package csci240.prinCad.model;

import java.util.ArrayList;

public class ModelData {
	final private ArrayList<CadItem> _items;
	
	public ModelData(ArrayList<CadItem> items) {
		_items = new ArrayList<CadItem>();
		for (CadItem item : items) {
			_items.add(item.copy());
		}
	}
	
	public ArrayList<CadItem> getItems() {
		return _items;
	}
	
}
