package util;
public class Array<T> {
	Object[] data;
	int count;
	
	public Array() {
		data = new Object[1];
		count = 0;
	}
	
	public int getSize() {
		return count;
	}
	
	public int getCapacity() {
		return data.length;
	}
	
	/**
	 * Mesmo que o "cast" nao seja uma operacao segura, como os
	 * dodos que podem ser inseridos serao, pelo menos, do tipo T,
	 * ou de um tipo derivado, podemos com seguranca fazer este "cast".
	 */
	@SuppressWarnings("unchecked")
	public T get(int index) {
		if (index < 0 || index >= count)
			throw new ArrayIndexOutOfBoundsException("Indice invalido.");
		return (T)data[index];
	}
	
	public void add(T value) {
		insert(count, value);
	}
	
	public void insert(int index, T value) {
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

	public void remove(int index) {
		if (index < 0 || index >= count)
			throw new ArrayIndexOutOfBoundsException("Indice invalido.");
		System.arraycopy(data, index, data, index-1, count - index);
		count--;
	}
}
