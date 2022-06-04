
/**
 * Arcara Alessio
 * 0900061028
 * alessio.arcara@studio.unibo.it
 * 
 * To compile: javac Esercizio2.java
 * To execute: java Esercizio2 <name file>
*/
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
    // starting from x, a prime number, x*2 and find nearest prime number and so on;
    final static int[] primeNumbers = { 17, 37, 79, 158, 331, 673 };

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
    private ArrayList<LinkedList<Entry<V>>> lists;
    int n;
    int m;

    public HashTable(int initialCapacity) {
        if (initialCapacity > 0) {
            this.m = (int) Math.pow(2, initialCapacity);
            this.lists = new ArrayList<>(initialCapacity);
            for (int i = 0; i < m; i++)
                this.lists.add(new LinkedList<Entry<V>>());
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
        return find(k, lists.get(HashCalculator.hash(k, m)));
    }

    public void insert(String k, V v) {
        int i = HashCalculator.hash(k, m);
        Entry<V> e = find(k, lists.get(i));
        if (e == null)
            this.lists.get(i).add(new Entry<>(k, v, i));
        else
            e.v = v;
        n++;
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
    HashTable<V> hashTable;

    public HashDictionary(int initialCapacity) {
        hashTable = new HashTable<>(initialCapacity);
    }

    public void insert(String key, V value) {
        hashTable.insert(key, value);
    }

    public void lookup(String key) {
        Entry<V> e = hashTable.lookup(key);
        System.out.println(
                "verifica presenza elemento: input " + key + "; output " + (e != null ? "‘elemento presente’"
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

/**
 * Esercizio2
 */
public class Esercizio2 {

    public static void writeFile(String outputf) {
        try {
            Random r = new Random();
            FileWriter f = new FileWriter(outputf);

            final int n = r.nextInt(100);
            final int m = r.nextInt(n / 10);

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

        writeFile(args[0]);
        HashDictionary<Character> D = readFile(args[0]);
        D.lookup("Chiave1");
        D.printList(1);
        D.printListSize(1);
    }
}