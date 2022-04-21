package Trees;

public class BinarySearchTree<K extends Comparable<? super K>, V> implements Comparable<K> {
    private K key;
    private V value;
    private BinarySearchTree<K, V> parent, left, right;

    public BinarySearchTree(K key, V value) {
        this.value = value;
        this.key = key;
        this.left = null;
        this.right = null;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public BinarySearchTree<K, V> getParent() {
        return parent;
    }

    public BinarySearchTree<K, V> getLeft() {
        return left;
    }

    public BinarySearchTree<K, V> getRight() {
        return right;
    }

    // iterative
    // public BinarySearchTree<K, V> lookupNode(BinarySearchTree<K, V> t, K key) {
    // while (t != null && t.key != key)
    // t = key.compareTo(key) < 0 ? t.left : t.right;
    // return t;
    // }

    // recursive
    public BinarySearchTree<K, V> lookupNode(BinarySearchTree<K, V> t, K key) {
        if (t == null || t.key.compareTo(key) == 0) {
            return t;
        } else {
            return lookupNode(key.compareTo(t.key) < 0 ? t.left : t.right, key);
        }
    }

    public static <K extends Comparable<? super K>, V> BinarySearchTree<K, V> insertNode(BinarySearchTree<K, V> t,
            K key, V value) {
        BinarySearchTree<K, V> p = null;
        BinarySearchTree<K, V> u = t;

        while (u != null && u.key != key) {
            p = u;
            u = key.compareTo(u.key) < 0 ? u.left : u.right;
        }
        if (u != null && u.key == key) {
            u.value = value;
        } else {
            BinarySearchTree<K, V> n = new BinarySearchTree<>(key, value);
            link(p, n, key);
            if (p == null) {
                t = n;
            }
        }

        return t;
    }

    private static <K extends Comparable<? super K>, V> void link(BinarySearchTree<K, V> p, BinarySearchTree<K, V> u,
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

    // public BinarySearchTree<K, V> removeNode(K key) {
    // BinarySearchTree<K, V> T = this, u = lookupNode(T, key);
    // if (u != null) {
    // /**
    // * Caso 3
    // */
    // if (u.left != null && u.right != null) {
    // BinarySearchTree<K, V> s = successorNode(u);
    // u.key = s.key;
    // u.value = s.value;
    // u = s;
    // key = s.key;
    // }
    // /**
    // * Caso 2
    // */
    // BinarySearchTree<K, V> t;
    // if (u.left != null && u.right == null) {
    // t = u.left;
    // }
    // /**
    // * Caso 2 - 1
    // */
    // else {
    // t = u.right;
    // }
    // link(u.parent, t, key);
    // if (u.parent == null) {
    // T = t;
    // }
    // }
    // return T;
    // }

    public BinarySearchTree<K, V> removeNode(K key) {
        BinarySearchTree<K, V> T = this, u = lookupNode(T, key);
        if (u != null) {
            /**
             * Caso 1
             */
            if (u.left == null && u.right == null) {
                if (u.parent != null) {
                    link(u.parent, null, key);
                }
                u = null;
            }
            /**
             * Caso 3
             */
            else if (u.left != null && u.right != null) {
                BinarySearchTree<K, V> s = successorNode(u);
                u.key = s.key;
                u.value = s.value;
                key = s.key;
                link(s.parent, s.right, key);
                s = null;
            }
            /**
             * Caso 2
             */
            else if (u.left != null && u.right != null) {
                link(u.parent, u.left, key);
                if (u.parent == null) {
                    T = u.left;
                }
            } else {
                link(u.parent, u.right, key);
                if (u.parent == null) {
                    T = u.right;
                }
            }
        }
        return T;
    }

    public BinarySearchTree<K, V> successorNode(BinarySearchTree<K, V> t) {
        if (t == null) {
            return t;
        } else if (t.right != null) {
            return min(t.right);
        } else {
            BinarySearchTree<K, V> p = t.parent;
            while (p != null && t == p.right) {
                t = p;
                p = p.parent;
            }
            return p;
        }
    }

    public BinarySearchTree<K, V> predecessorNode(BinarySearchTree<K, V> t) {
        if (t == null) {
            return t;
        } else if (t.left != null) {
            return max(t.left);
        } else {
            BinarySearchTree<K, V> p = t.parent;
            while (p != null && t == p.left) {
                t = p;
                p = p.parent;
            }
            return p;
        }
    }

    public BinarySearchTree<K, V> min(BinarySearchTree<K, V> t) {
        BinarySearchTree<K, V> u = t;
        while (u.left != null) {
            u = u.left;
        }
        return u;
    }

    public BinarySearchTree<K, V> max(BinarySearchTree<K, V> t) {
        BinarySearchTree<K, V> u = t;
        while (u.right != null) {
            u = u.right;
        }
        return u;
    }

    @Override
    public int compareTo(K o) {
        return key.compareTo(o);
    }

    public void printAll() {
        BinarySearchTree<K, V> u = min(this);
        while (u != null) {
            System.out.println("KEY: " + u.key + " VALUE: " + u.value);
            u = successorNode(u);
        }
    }
}
