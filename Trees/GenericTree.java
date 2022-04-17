package Trees;

import Containers.ListQueue;

public class GenericTree<T> extends Tree<T> {
    private GenericTree<T> child;
    private GenericTree<T> sibling;

    public GenericTree(T value) {
        super(value);
        this.parent = null;
        this.child = null;
        this.sibling = null;
    }

    public Tree<T> getParent() {
        return parent;
    }

    public GenericTree<T> leftMostChild() {
        return child;
    }

    public GenericTree<T> rightSibling() {
        return sibling;
    }

    public void insertChild(GenericTree<T> t) {
        t.parent = this;
        t.sibling = child;
        child = t;
    }

    public void insertSibling(GenericTree<T> t) {
        t.parent = this.parent;
        t.sibling = sibling;
        sibling = t;
    }

    public void delete(GenericTree<T> t) {
        GenericTree<T> u = t.leftMostChild();
        while (u != null) {
            GenericTree<T> next = u.rightSibling();
            delete(u);
            u = next;
        }
        t = null;
    }

    public void deleteChild() {
        GenericTree<T> newChild = child.rightSibling();
        delete(child);
        child = newChild;
    }

    public void deleteSibling() {
        GenericTree<T> newBrother = sibling.rightSibling();
        delete(sibling);
        child = newBrother;
    }

    public static <T> int count(GenericTree<T> t) {
        GenericTree<T> u = t.leftMostChild();
        int k = 0;
        while (u != null) {
            GenericTree<T> next = u.rightSibling();
            k += count(u) + 1;
            u = next;
        }
        return k;
    }

    private static <T> void bfs(GenericTree<T> t, Callable<T> func) {
        ListQueue<GenericTree<T>> q = new ListQueue<>();
        q.enqueue(t);
        while (q.isEmpty() != true) {
            GenericTree<T> u = q.dequeue();
            func.call(u.getValue());
            u = u.leftMostChild();
            while (u != null) {
                q.enqueue(u);
                u = u.rightSibling();
            }
        }
    }

    public void printAll() {
        bfs(this, (T value) -> {
            System.out.println(value);
        });
    }
}
