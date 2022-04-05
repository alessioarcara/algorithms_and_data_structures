package Containers;

public class VectorStack<T> {
    private T[] stack;
    private int n = 0;

    @SuppressWarnings("unchecked")
    VectorStack(int initialCapacity) {
        if (initialCapacity > 0) {
            stack = (T[]) new Object[initialCapacity];
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
            throw new IllegalStateException("Stack is empty");
        return stack[n - 1];
    }

    public T pop() {
        if (isEmpty())
            throw new IllegalStateException("Stack is empty");
        return stack[--n];
    }

    public void push(T value) {
        if (n == stack.length) {
            throw new IllegalStateException("Stack is full");
        }
        stack[n++] = value;
    }
}
