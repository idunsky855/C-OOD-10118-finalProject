package model;

import java.io.Serializable;
import java.util.ArrayList;

public class MySet<T> implements Serializable {

	private static final long serialVersionUID = 4515745442932175651L; // auto-generated by serializable
	private ArrayList<T> array;

	public MySet() {
		array = new ArrayList<T>();
	}

	public boolean add(T other) throws Exception {
		if (array.contains(other))
			throw new Exception("This object already exist in the set!");
		return array.add(other);
	}

	public void remove(int index) throws IndexOutOfBoundsException {
		array.remove(index);
	}

	public T get(int index) throws IndexOutOfBoundsException {
		return array.get(index);
	}

	public void clear() {
		array.clear();
	}

	public boolean contains(Object object) {
		return array.contains(object);
	}

	public boolean isEmpty() {
		return array.isEmpty();
	}

	public int size() {
		return array.size();
	}

	public ArrayList<T> getArray() {
		return array;
	}

	public String toString() {
		return array.toString();
	}

}