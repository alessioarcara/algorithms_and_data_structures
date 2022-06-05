
/**
 * Arcara Alessio
 * 0900061028
 * alessio.arcara@studio.unibo.it
 * 
 * 
 * 
 * To compile: javac Esercizio2.java
 * To execute: java Esercizio2 <name file>
*/
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Random;
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

class HashCalculator {
    // division method
    public static int hash(String k, int m) {
        String b = "";
        for (char ch : k.toCharArray()) {
            b += Integer.toBinaryString((int) ch);
        }
        return (int) (Long.parseLong(b, 2) % m);
    }
}

class HashTable<V> {
    private LinkedList<Entry<V>>[] lists;
    int n;
    int m;

    public HashTable(int initialCapacity) {
        if (initialCapacity > 0) {
            this.m = initialCapacity;
            @SuppressWarnings("unchecked")
            final LinkedList<Entry<V>>[] lists = new LinkedList[initialCapacity];
            this.lists = lists;
            for (int i = 0; i < m; i++)
                this.lists[i] = new LinkedList<Entry<V>>();
        } else {
            throw new IllegalArgumentException("Illegal Capacity "
                    + initialCapacity);
        }
    }

    private Entry<V> find(String k, LinkedList<Entry<V>> list) {
        for (Entry<V> e : list)
            if (e.k.equals(k))
                return e;
        return null;
    }

    public Entry<V> lookup(String k) {
        return find(k, lists[HashCalculator.hash(k, m)]);
    }

    // Overloading
    public Entry<V> lookup(String k, int hs) {
        return find(k, lists[hs]);
    }

    public void insert(String k, V v) {
        int i = HashCalculator.hash(k, m);
        Entry<V> e = find(k, lists[i]);
        if (e == null)
            this.lists[i].add(new Entry<>(k, v, i));
        else
            e.v = v;
        n++;
    }

    // Overloading
    public void insert(String k, V v, int hs) {
        Entry<V> e = find(k, lists[hs]);
        if (e == null)
            this.lists[hs].add(new Entry<>(k, v, hs));
        else
            e.v = v;
        n++;
    }

    public LinkedList<Entry<V>> list(int hs) {
        return lists[hs];
    }

    public int listSize(int hs) {
        return lists[hs].size();
    }
}

class HashDictionary<V> {
    HashTable<V> hashTable;

    public HashDictionary(int initialCapacity) {
        hashTable = new HashTable<>(initialCapacity);
    }

    public void insert(String key, V value, int hs) {
        hashTable.insert(key, value, hs);
    }

    public void lookup(String key, V value, int hs) {
        Entry<V> e = hashTable.lookup(key, hs);
        System.out
                .println(String.format("verifica presenza elemento: input <%s, %s, %d>; output ", key, value, hs)
                        + (e != null ? "‘elemento presente’"
                                : "‘elemento non presente’"));
    }

    public void printList(int hs) {
        String listString = hashTable.list(hs).toString();
        System.out.printf("stampa lista in base al valore di hs: input %d output %s\n", hs,
                listString.substring(1, listString.length() - 1));
    }

    public void printListSize(int hs) {
        System.out.printf("ricerca in base al valore hs: input %d; output %d\n", hs, hashTable.listSize(hs));
    }
}

public class Esercizio2 {
    private final static long seed = 900061028L;
    private final static int UPPER_BOUND = 100;
    private final static int LOWER_BOUND = 50;
    private final static int BIT_LENGTH = 5;

    /*
     * method for make a file containing values N, K and triplets <key, value, hs>
     */
    public static void writeFile(String outputf) {
        try {
            Random r = new Random(seed);
            FileWriter f = new FileWriter(outputf);

            final int n = r.nextInt((UPPER_BOUND - LOWER_BOUND) + 1) + LOWER_BOUND;
            final int m = BigInteger.probablePrime(BIT_LENGTH, r).intValue();

            f.write(n + " " + m + "\n");

            for (int i = 1; i <= n; i++) {
                char newInfo = (char) (r.nextInt('z' - 'a') + 'a');
                String newKey = "Chiave" + i;
                final String s = newKey + " " + newInfo + " " + HashCalculator.hash(newKey, m) + "\n";
                f.write(s);
            }
            f.close();
        } catch (IOException ex) {
            System.err.println(ex);
            System.exit(1);
        }
    }

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

                D.insert(key, val, hs);
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
        // writeFile(args[0]);
        HashDictionary<Character> D = readFile(args[0]);
        D.lookup("Chiave1", 'b', 19);
        D.lookup("Chiave101", 'a', 13);
        D.printList(0);
        D.printListSize(0);
    }
}