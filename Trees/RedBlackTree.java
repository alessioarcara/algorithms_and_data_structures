package Trees;

import Containers.ListQueue;

public class RedBlackTree<K extends Comparable<K>, V> {
    static final int BLACK = 1;
    static final int RED = 0;

    private Node nilNode;
    private Node root;

    private class Node {
        private K key;
        private V value;
        private Node parent, left, right;
        private int color;

        public Node() {
            this.color = BLACK;
            this.parent = this;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.color = RED;
            this.left = this.right = nilNode;
        }

        @Override
        public String toString() {
            return ("<" + this.key + "," + this.value + "," + (color == 1 ? "black" : "red") + ">");
        }
    }

    public RedBlackTree() {
        this.root = this.nilNode = new Node();
    }

    public Node getRoot() {
        return root;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node p = x.parent;

        x.right = y.left; // il sottoalbero T2 diventa figlio destro di x
        if (y.left != nilNode) {
            y.left.parent = x;
        }
        y.left = x; // x diventa figlio sinistro di y
        x.parent = y;
        y.parent = p; // y diventa figlio di p
        if (p != nilNode) {
            if (p.left == x) {
                p.left = y;
            } else {
                p.right = y;
            }
        }
        return y;
    }

    private Node rotateRight(Node x) {
        Node y = x.left;
        Node p = x.parent;

        x.left = y.right; // il sottoalbero T2 diventa figlio sinistro di x
        if (y.right != nilNode) {
            y.right.parent = x;
        }
        y.right = x; // x diventa figlio destro di y
        x.parent = y;
        y.parent = p; // y diventa figlio di p
        if (p != nilNode) {
            if (p.right == x) {
                p.right = y;
            } else {
                p.left = y;
            }
        }
        return y;
    }

    private void link(Node p, Node u,
            K key) {
        u.parent = p;
        if (p != nilNode) {
            if (key.compareTo(p.key) < 0) {
                p.left = u;
            } else {
                p.right = u;
            }
        }
    }

    public void insertNode(K key, V value) {
        Node p = nilNode;
        Node u = root;

        while (u != nilNode && u.key != key) {
            p = u;
            u = key.compareTo(u.key) < 0 ? u.left : u.right;
        }
        if (u != nilNode && u.key == key) {
            u.value = value;
        } else {
            Node t = new Node(key, value);
            link(p, t, key);
            balanceInsert(t);
            while (t.parent != nilNode) {
                t = t.parent;
            }
            root = t;
        }
    }

    private void balanceInsert(Node t) {
        while (t != null) {
            Node p = t.parent;
            Node n = p != nilNode ? p.parent : nilNode;
            Node z = n == nilNode ? nilNode : n.left == p ? n.right : n.left;

            if (p == nilNode) { // Caso 1
                t.color = BLACK;
                t = null;
            } else if (p.color == BLACK) { // Caso 2
                t = null;
            } else if (z.color == RED) { // Caso 3
                p.color = z.color = BLACK;
                n.color = RED;
                t = n;
            } else {
                if (t == p.right && p == n.left) { // caso 4
                    rotateLeft(p);
                    t = p;
                } else if (t == p.left && p == n.right) {
                    rotateRight(p);
                    t = p;
                } else { // caso 5
                    if (t == p.left && p == n.left) {
                        rotateRight(n);
                    } else if (t == p.right && p == n.right) {
                        rotateLeft(n);
                    }
                    p.color = BLACK;
                    n.color = RED;
                    t = null;
                }
            }
        }
    }

    public void printAll(Node t) {
        ListQueue<Node> q = new ListQueue<>();
        q.enqueue(t);
        while (q.isEmpty() != true) {
            Node u = q.dequeue();
            System.out.println(u.toString());
            if (u.left != null && u.left.key != null) {
                q.enqueue(u.left);
            }
            if (u.right != null && u.right.key != null) {
                q.enqueue(u.right);
            }
        }
    }
}
