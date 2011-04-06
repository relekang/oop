package oving9;

import java.util.List;
import java.util.ArrayList;

public abstract class ObservableList {
	ArrayList<ListListener> listListeners = new ArrayList<ListListener>();
	public void addListListener(ListListener listener) {
		if(!listListeners.contains(listener)) listListeners.add(listener);
	}
	public void removeListListener(ListListener listener) {
		listListeners.remove(listener);
	}
	protected void fireListChanged(int indx1, int indx2){
		for (ListListener listner : listListeners) {
			listner.listChanged(this, indx1, indx2);
		}
	}
	protected abstract List<Object> getList();
	public int size(){
		return getList().size();
	}
	protected void addElement(int index, Object element){
		getList().add(index, element);
		fireListChanged(index, getList().size()-1);
	}
	protected void removeElement(int index){
		getList().remove(index);
		fireListChanged(index, getList().size());
	}
}
