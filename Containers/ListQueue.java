package Containers;

public class ListQueue<T> {
    private LinkedList<T> linkedList;

    public ListQueue() {
        linkedList = new LinkedList<>();
    }

    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    public T top() {
        if (isEmpty())
            throw new IllegalStateException("Queue is empty");
        return linkedList.head().value;
    }

    public T dequeue() {
        if (isEmpty())
            throw new IllegalStateException("Queue is empty");
        Node<T> t = linkedList.tail();
        linkedList.remove(t);
        return t.value;
    }

    public void enqueue(T value) {
        linkedList.insert(linkedList.head(), value);
    }
}
