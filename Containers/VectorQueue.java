package Containers;

public class VectorQueue<T> {
    private T[] queue; // Vector containing the elements
    private int n = 0; // Number of elements in the queue
    private int testa = 0; // Top element of the queue

    @SuppressWarnings("unchecked")
    public VectorQueue(int initialCapacity) {
        if (initialCapacity > 0) {
            queue = (T[]) new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity "
                    + initialCapacity);
        }
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public T top() {
        if (isEmpty())
            throw new IllegalStateException("Queue is empty");
        return queue[testa];
    }

    public T dequeue() {
        if (isEmpty())
            throw new IllegalStateException("Queue is empty");
        T t = queue[testa];
        testa = (testa + 1) % queue.length;
        n--;
        return t;
    }

    public void enqueue(T value) {
        if (n == queue.length)
            throw new IllegalStateException("Queue is full");
        queue[(testa + n) % queue.length] = value;
        n++;
    }
}
