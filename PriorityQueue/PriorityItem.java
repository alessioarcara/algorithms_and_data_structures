package PriorityQueue;

public class PriorityItem<T> {
    private int priority;
    private T value;
    private int pos;

    public PriorityItem(int priority, T value, int pos) {
        this.priority = priority;
        this.value = value;
        this.pos = pos; // Posizione nel vettore heap
    }

    public static <T> void swap(PriorityItem<T>[] H, int i, int j) {
        PriorityItem<T> temp = H[i];
        H[i] = H[j];
        H[j] = temp;
        H[i].pos = i;
        H[j].pos = j;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPos() {
        return pos;
    }

    public T getValue() {
        return value;
    }
}
