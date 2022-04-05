package Containers;

public class ArrayList<T> {
    private int size = 0;
    private T[] array;

    @SuppressWarnings("unchecked")
    ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            array = (T[]) new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity " + initialCapacity);
        }
    }

    @SuppressWarnings("unchecked")
    private void doubleStorage() {
        T[] newArray = (T[]) new Object[2 * array.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    private void checkIndex(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T value) {
        if (size == array.length) {
            doubleStorage();
        }
        array[size++] = value;
    }

    public void add(T value, int index) {
        if (size == array.length) {
            doubleStorage();
        }
        System.arraycopy(array, index,
                array, index + 1, size++ - index);
        array[index] = value;
    }

    public T remove(int index) {
        checkIndex(index);
        T oldValue = array[index];
        System.arraycopy(array, index + 1,
                array, index, size - index);
        array[size--] = null;
        return oldValue;
    }

    public T get(int index) {
        checkIndex(index);
        return array[index];
    }

    public void printAll() {
        for (Object object : array) {
            System.out.print(object + " ");
        }
    }
}
