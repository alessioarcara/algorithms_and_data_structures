
/**
 * Arcara Alessio
 * 0900061028
 * alessio.arcara@studio.unibo.it
 * 
 * To compile: javac Esercizio2.java
 * To execute: java Esercizio2 <name file>
*/
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

class Entry<V> {
    String k;
    V v;
    int hs;

    Entry(String k, V v, int hs) {
        this.k = k;
        this.v = v;
        this.hs = hs;
    }

    @Override
    public String toString() {
        return "<" + k + "," + v.toString() + "," + hs + ">";
    }
}

class Hash<V> {
    private ArrayList<LinkedList<Entry<V>>> lists;
    int m;

    public Hash(int initialCapacity) {
        if (initialCapacity > 0) {
            this.m = initialCapacity;
            this.lists = new ArrayList<>(initialCapacity);

            for (int i = 0; i < m; i++) {
                this.lists.add(new LinkedList<Entry<V>>());
            }
        } else {
            throw new IllegalArgumentException("Illegal Capacity "
                    + initialCapacity);
        }
    }

    private int hash(String k) {
        String b = "";
        for (char ch : k.toCharArray()) {
            b += Integer.toBinaryString((int) ch);
        }
        return (int) (Long.parseLong(b, 2) % m);
    }

    private Entry<V> find(String k, LinkedList<Entry<V>> list) {
        Entry<V> eFound = null;
        for (Entry<V> e : list) {
            if (e.k == k) {
                eFound = e;
                break;
            }
        }
        return eFound;
    }

    public Entry<V> lookup(String k) {
        int i = hash(k);
        Entry<V> e = find(k, lists.get(i));
        if (e != null) {
            return e;
        } else {
            return null;
        }
    }

    public void insert(String k, V v) {
        int i = hash(k);
        Entry<V> e = find(k, lists.get(i));
        if (e == null) {
            Entry<V> newEntry = new Entry<>(k, v, i);
            this.lists.get(i).add(newEntry);
        } else {
            e.v = v;
        }
    }

    public LinkedList<Entry<V>> list(int hs) {
        if (hs < 0 || hs >= m)
            throw new IndexOutOfBoundsException("Index out of bounds: " + hs);
        return lists.get(hs);
    }

    public int listSize(int hs) {
        if (hs < 0 || hs >= m)
            throw new IndexOutOfBoundsException("Index out of bounds: " + hs);
        return lists.get(hs).size();
    }
}

/**
 * HashDictionary
 */
class HashDictionary<V> {
    Hash<V> hash;

    public HashDictionary(int initialCapacity) {
        hash = new Hash<>(initialCapacity);
    }

    public void insert(String key, V value) {
        hash.insert(key, value);
    }

    public void lookup(String key) {
        Entry<V> e = hash.lookup(key);
        System.out.println(
                "verifica presenza elemento: input " + key + "; output " + (e != null ? "‘elemento presente’"
                        : "‘elemento non presente’"));
    }

    public void printList(int hs) {
        String listString = hash.list(hs).toString();
        System.out.printf("stampa lista in base al valore di hs: input %d output %s\n", hs,
                listString.substring(1, listString.length() - 1));
    }

    public void printListSize(int hs) {
        System.out.printf("ricerca in base al valore hs: input %d; output %d\n", hs, hash.listSize(hs));
    }
}

/**
 * Esercizio2
 */
public class Esercizio2 {
    public static <V> HashDictionary<Character> readFile(String inputf) {
        Locale.setDefault(Locale.US);

        try {
            Scanner f = new Scanner(new FileReader(inputf));
            int n = f.nextInt();
            int k = f.nextInt();

            HashDictionary<Character> D = new HashDictionary<>(k);

            for (int i = 0; i < n; i++) {
                final String key = f.next();
                final char val = f.next().charAt(0);
                final int hs = f.nextInt();

                D.insert(key, val);
            }
            return D;
        } catch (IOException ex) {
            System.err.println(ex);
            System.exit(1);
        }
        return null;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Esercizio2 <file name>");
            System.exit(1);
        }

        HashDictionary<Character> D = readFile(args[0]);
        D.lookup("Chiave1");
        D.printList(1);
        D.printListSize(1);
    }
}