package Containers;

public class ListStack<T> {
    private LinkedList<T> linkedList;

    public ListStack() {
        linkedList = new LinkedList<>();
    }

    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    public T top() {
        if (linkedList.isEmpty())
            throw new IllegalStateException("Stack is empty");
        return linkedList.head().value;
    }

    public T pop() {
        if (linkedList.isEmpty())
            throw new IllegalStateException("Stack is empty");
        Node<T> t = linkedList.head();
        linkedList.remove(t);
        return t.value;
    }

    public void push(T value) {
        linkedList.append(value);
    }
}
