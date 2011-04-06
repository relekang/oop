package oving9;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HighscoreList extends ObservableList implements Iterable<Comparable>{
	private int maxSize;
	public HighscoreList(int maxSize) {
		this.maxSize = maxSize;
	}
	public void addResult(Comparable input) {
		if(this.size() < maxSize) this.addElement(this.size(), input);
	}
	
	@Override
	protected List<Object> getList() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Iterator<Comparable> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
