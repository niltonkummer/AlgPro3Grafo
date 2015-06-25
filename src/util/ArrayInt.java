package util;
public class ArrayInt {
	int[] data;
	int count;
	
	public ArrayInt() {
		data = new int[1];
		count = 0;
	}
	
	public int getSize() {
		return count;
	}
	
	public int getCapacity() {
		return data.length;
	}
	
	public int get(int index) {
		if (index < 0 || index >= count)
			throw new ArrayIndexOutOfBoundsException("Indice invalido.");
		return data[index];
	}
	
	public void add(int value) {
		insert(count, value);
	}
	
	public void insert(int index, int value) {
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
		int[] new_array = new int[data.length * 2];
		System.arraycopy(data, 0, new_array, 0, data.length);
		data = new_array;
	}
	
	private void moveItens(int index) {
		System.arraycopy(data, index, data, index+1, count - index);
	}

	public void remove(int index) {
		if (index < 0 || index >= count)
			throw new ArrayIndexOutOfBoundsException("Indice invalido.");
		System.arraycopy(data, index, data, index-1, count - index);
		count--;
	}
}
