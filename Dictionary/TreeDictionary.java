package Dictionary;

import Trees.BinarySearchTree;
import Trees.RedBlackTree;

/**
 * TreeDictionary
 */
public class TreeDictionary<K extends Comparable<K>, V> {
    RedBlackTree<K, V> tree;

    public TreeDictionary() {
        tree = new RedBlackTree<>();
    }

    // public V lookup(K key) {
    // BinarySearchTree<K, V> t = tree.lookupNode(tree, key);
    // if (t != null) {
    // return t.getValue();
    // } else {
    // return null;
    // }
    // }

    public void insert(K key, V value) {
        tree.insertNode(tree.getRoot(), key, value);
    }

    // public void remove(K key) {
    // tree = tree.removeNode(key);
    // }

    public void printAll() {
        if (tree != null) {
            tree.printAll(tree.getRoot());
        }
    }
}