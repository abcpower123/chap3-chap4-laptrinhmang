package model;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class CustomListModel extends AbstractListModel {
	ArrayList<String> names = new ArrayList<>();
	
	
	public CustomListModel() {
		super();
		
	}
	
	public void add(String item) {
		names.add(item);
	}
	public void remove(String item) {
		for (String string : names) {
			if(string.equals(item)) {
				names.remove(string);
				break;
			}
		}
	}
	public int getSize() {
		return names.size();
	}
	public Object getElementAt(int index) {
		return names.get(index);
	}

}
