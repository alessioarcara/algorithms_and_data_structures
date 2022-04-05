package Containers;

public class Node<T> {
    Node<T> succ;
    Node<T> pred;
    T value;

    Node(T value) {
        this.value = value;
        this.pred = this;
        this.succ = this;
    }
}
