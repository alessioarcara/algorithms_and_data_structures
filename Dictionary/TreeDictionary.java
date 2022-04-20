package Dictionary;

import Trees.BinarySearchTree;

/**
 * TreeDictionary
 */
public class TreeDictionary<K extends Comparable<? super K>, V> {
    BinarySearchTree<K, V> tree;

    public TreeDictionary() {
        tree = null;
    }

    public V lookup(K key) {
        BinarySearchTree<K, V> t = tree.lookupNode(tree, key);
        if (t != null) {
            return t.getValue();
        } else {
            return null;
        }
    }

    public void insert(K key, V value) {
        tree = BinarySearchTree.insertNode(tree, key, value);
    }

    public void remove(K key) {
        tree = tree.removeNode(key);
    }

    public void printAll() {
        if (tree != null) {
            tree.printAll();
        }
    }
}