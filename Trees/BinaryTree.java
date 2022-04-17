package Trees;

import Containers.ListQueue;

public class BinaryTree<T> extends Tree<T> {
    private BinaryTree<T> left;
    private BinaryTree<T> right;

    public BinaryTree(T value) {
        super(value);
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    public Tree<T> getParent() {
        return parent;
    }

    public BinaryTree<T> getLeft() {
        return left;
    }

    public BinaryTree<T> getRight() {
        return right;
    }

    public void insertLeft(BinaryTree<T> t) {
        if (left == null) {
            t.parent = this;
            left = t;
        }
    }

    public void insertRight(BinaryTree<T> t) {
        if (right == null) {
            t.parent = this;
            right = t;
        }
    }

    public void deleteLeft() {
        if (left != null) {
            left.deleteLeft();
            left.deleteRight();
            left = null;
        }
    }

    public void deleteRight() {
        if (right != null) {
            right.deleteLeft();
            right.deleteRight();
            right = null;
        }
    }

    public static <T> BinaryTree<T> search(BinaryTree<T> t, T value) {
        BinaryTree<T> l = null, r = null;

        if (t != null) {
            if (t.getValue() == value)
                return t;
            l = search(t.left, value);
            r = search(t.right, value);
        }
        return l != null ? l : r;
    }

    public static <T> int count(BinaryTree<T> t) {
        if (t == null) {
            return 0;
        } else {
            int count_l = count(t.left);
            int count_r = count(t.right);
            return count_l + count_r + 1;
        }
    }

    public static <T> void bfs(BinaryTree<T> t, Callable<T> func) {
        ListQueue<BinaryTree<T>> q = new ListQueue<>();
        q.enqueue(t);
        while (q.isEmpty() != true) {
            BinaryTree<T> u = q.dequeue();
            func.call(u.getValue());
            if (u.left != null) {
                q.enqueue(u.left);
            }
            if (u.right != null) {
                q.enqueue(u.right);
            }
        }
    }

    public void printAll() {
        bfs(this, (T value) -> {
            System.out.println(value);
        });
    }
}
