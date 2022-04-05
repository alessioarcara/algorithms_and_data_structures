package Containers;

/**
 * List bidirectional, circular, with sentinel
 */

public class LinkedList<T> {
    private Node<T> sentinel;

    LinkedList() {
        this.sentinel = new Node<>(null);
    }

    public Node<T> head() {
        return this.sentinel.succ;
    }

    public Node<T> tail() {
        return this.sentinel.pred;
    }

    public Node<T> next(Node<T> pos) {
        return pos.succ;
    }

    public Node<T> prev(Node<T> pos) {
        return pos.pred;
    }

    public boolean isEmpty() {
        return (this.sentinel.succ == this.sentinel.pred) && (this.sentinel.succ == this.sentinel);
    }

    public boolean finished(Node<T> pos) {
        return this.sentinel == pos;
    }

    public Node<T> append(T value) {
        Node<T> t = new Node<>(value);
        t.pred = this.tail();
        t.succ = this.sentinel;
        this.tail().succ = t;
        this.sentinel.pred = t;
        return t;
    }

    public Node<T> insert(Node<T> pos, T value) {
        Node<T> t = new Node<>(value);
        t.pred = pos.pred;
        pos.pred.succ = t;
        t.succ = pos;
        pos.pred = t;
        return t;
    }

    public Node<T> remove(Node<T> pos) {
        pos.pred.succ = pos.succ;
        pos.succ.pred = pos.pred;
        Node<T> t = pos.succ;
        pos = null;
        return t;
    }

    public T read(Node<T> pos) {
        return pos.value;
    }

    public void write(Node<T> pos, T value) {
        pos.value = value;
    }

    public void printAll(Node<T> pos) {
        if (pos.value == null) {
            return;
        } else {
            System.out.print(pos.value + " ");
            this.printAll(pos.succ);
        }
    }
}
