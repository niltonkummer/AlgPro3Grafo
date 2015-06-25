package util;

import java.util.Arrays;
import java.util.Comparator;


public class ArrayObject {
	Object[] data;
	int count;
	
	public ArrayObject() {
		data = new Object[1];
		count = 0;
	}
	
    public Object[] getList() {
        return data;
    }
	public int getSize() {
		return count;
	}
	
	public int getCapacity() {
		return data.length;
	}
	
	public Object get(int index) {
		if (index < 0 || index >= count)
			throw new ArrayIndexOutOfBoundsException("Indice invalido.");
		return data[index];
	}
	
	public void add(Object value) {
		insert(count, value);
	}
	
	public void insert(int index, Object value) {
		if (index < 0 || index > count)
			throw new ArrayIndexOutOfBoundsException("Indice invalido.");
		if (isDataFull())
			resizeArray();
		moveItens(index);
		data[index] = value;
		count++;
	}

	private boolean isDataFull() {
		return count == data.length;
	}
	
	private void resizeArray() {
		Object[] new_array = new Object[data.length * 2];
		System.arraycopy(data, 0, new_array, 0, data.length);
		data = new_array;
	}
	
	private void moveItens(int index) {
		System.arraycopy(data, index, data, index+1, count - index);
	}

    public void sort(Comparator c) {
        Arrays.sort(data, c);
    }
    
    public int search(Object obj,Comparator c) {
        return Arrays.binarySearch(data, obj, c);
    }
    
	public void remove(int index) {
		if (index < 0 || index >= count)
			throw new ArrayIndexOutOfBoundsException("Indice invalido.");
		System.arraycopy(data, index, data, index-1, count - index);
		count--;
	}
}
