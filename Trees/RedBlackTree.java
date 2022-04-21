package Trees;

import Containers.ListQueue;

enum Color {
    RED,
    BLACK
}

public class RedBlackTree<K extends Comparable<K>, V> {

    private Node<K, V> root;
    private Node<K, V> nil = new Node<K, V>();

    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> parent, left, right;
        private Color color;

        public Node(K key, V value, Color color, Node<K, V> nilNode) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.left = this.right = nilNode;
        }

        public Node() {
            this.color = Color.BLACK;
        }
    }

    public RedBlackTree() {
    }

    public Node<K, V> getRoot() {
        return root;
    }

    private Node<K, V> rotateLeft(Node<K, V> x) {
        Node<K, V> y = x.right;
        Node<K, V> p = x.parent;

        x.right = y.left; // il sottoalbero T2 diventa figlio destro di x
        if (y.left != null) {
            y.left.parent = x;
        }
        y.left = x; // x diventa figlio sinistro di y
        x.parent = y;
        y.parent = p; // y diventa figlio di p
        if (p != null) {
            if (p.left == x) {
                p.left = y;
            } else {
                p.right = y;
            }
        }
        return y;
    }

    private Node<K, V> rotateRight(Node<K, V> x) {
        Node<K, V> y = x.left;
        Node<K, V> p = x.parent;

        x.left = y.right; // il sottoalbero T2 diventa figlio sinistro di x
        if (y.right != null) {
            y.right.parent = x;
        }
        y.right = x; // x diventa figlio destro di y
        x.parent = y;
        y.parent = p; // y diventa figlio di p
        if (p != null) {
            if (p.left == x) {
                p.left = y;
            } else {
                p.right = y;
            }
        }
        return y;
    }

    private void link(Node<K, V> p, Node<K, V> u,
            K key) {
        if (u != null) {
            u.parent = p;
        }
        if (p != null) {
            if (key.compareTo(p.key) < 0) {
                p.left = u;
            } else {
                p.right = u;
            }
        }
    }

    private void balanceInsert(Node<K, V> t) {
        t.color = Color.RED;
        while (t != null) {
            Node<K, V> p = t.parent;
            Node<K, V> n = p != null ? p.parent : null;
            Node<K, V> z = n == null ? null : n.left == p ? n.right : n.left;

            if (p == null) { // Caso 1
                t.color = Color.BLACK;
                t = null;
            } else if (p.color == Color.BLACK) { // Caso 2
                t = null;
            } else if (z.color == Color.RED) { // Caso 3
                p.color = Color.BLACK;
                z.color = Color.BLACK;
                n.color = Color.RED;
                t = n;
            } else {
                if (t == p.right && p == n.left) { // caso 4
                    n.left = rotateLeft(p);
                    t = p;
                } else if (t == p.right && p == n.left) {
                    n.right = rotateRight(p);
                    t = p;
                } else { // caso 5
                    if (t == p.left && p == n.left) {
                        n.left = rotateRight(n);
                    } else if (t == p.right && p == n.right) {
                        n.right = rotateLeft(n);
                    }
                    p.color = Color.BLACK;
                    n.color = Color.RED;
                    t = null;
                }
            }
        }
    }

    public void insertNode(Node<K, V> t, K key, V value) {
        Node<K, V> p = null;
        Node<K, V> u = t;

        while (u != null && u.key != null && u.key != key) {
            p = u;
            u = key.compareTo(u.key) < 0 ? u.left : u.right;
        }
        if (u != null && u.key == key) {
            u.value = value;
        } else {
            Node<K, V> n = new Node<>(key, value, Color.RED, nil);
            link(p, n, key);
            balanceInsert(n);
            while (n.parent != null) {
                n = n.parent;
            }
            root = n;
        }
    }

    public void printAll(Node<K, V> t) {
        ListQueue<Node<K, V>> q = new ListQueue<>();
        q.enqueue(t);
        while (q.isEmpty() != true) {
            Node<K, V> u = q.dequeue();
            System.out.println("KEY: " + u.key + " COLOR: " + u.color);
            if (u.left != null && u.left.key != null) {
                q.enqueue(u.left);
            }
            if (u.right != null && u.right.key != null) {
                q.enqueue(u.right);
            }
        }
    }
}
