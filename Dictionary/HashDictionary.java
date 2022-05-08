package Dictionary;

import Hashing.Hash;

/**
 * HashDictionary
 */
public class HashDictionary<V> {
    Hash<V> hash;

    public HashDictionary() {
        hash = new Hash<>(5);
    }

    public V lookup(String key) {
        return hash.lookup(key);
    }

    public void insert(String key, V value) {
        hash.insert(key, value);
    }

    public void remove(String key) {
        hash.remove(key);
    }

    public void printAll() {
        hash.printAll();
    }
}
