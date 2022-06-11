
/**
 * Arcara Alessio
 * 0900061028
 * alessio.arcara@studio.unibo.it
 * 
 * Si è realizzato il dizionario e una tabella hash sottostante con:
 * - funzione hash -> metodo della divisione
 * - gestione delle collisioni -> liste di trabocco
 * 
 * Per specifiche dell'esercizio il valore hash della chiave k viene letto alla lettura del file 
 * e congruentemente tutte le operazioni presuppongono che venga fornito il valore hash corretto [0,K-1].
 * 
 * Le chiavi con lo stesso valore hash vengono memorizzate in una lista: 
 * - l'operazione lookup scandaglia la lista per cercare l'elemento con quella chiave
 * - l'operazione insert scandaglia la lista per controllare se l'elemento è già presente
 * 
 * Servendosi del valore hash come indice del vettore, si accede alla lista in tempo Θ(1):
 * La ricerca senza successo tocca tutte le chiavi della lista corrispondente -> α
 * La ricerca con successo tocca in media metà delle chiavi nella lista corrispondente -> α/2
 *
 * Assumendo l'utilizzo di una buona funzione hash, e che dunque le chiavi vengono distribuite uniformemente,
 * le liste di trabocco avranno lunghezza pari al fattore di carico α, con α=N/K.
 * 
 * Pertanto, possiamo dire che il numero medio di accessi per lookup sarà Θ(1) + α
 * e che il numero di accessi per listsize sarà Θ(1).
 * 
 * Dizionario            
 * +++++++++++++++++++++++++++
 * + insert ->         O(1)+α+
 * + lookup ->         O(1)+α+
 * + list ->             O(1)+
 * + listsize ->         O(1)+
 * +++++++++++++++++++++++++++
 * 
 * Nota: per scelta, non si verifica se il valore info della tripla in input è uguale al valore info della tripla trovata. 
 * Nota2: mediante la procedura write è possibile generare un file contenente i valori N,K e le triple <key, value, hs>.
 * 
 * To compile: javac Esercizio2.java
 * To execute: java Esercizio2 <file name> <operation>
 * 
 * Possible operations            
 * --------------------------------------------------------------------------------------------------
 * - lookup <key> <value> <hs>     -> example: java Esercizio2.java triplets.txt lookup Chiave1 a 5 -
 * - list <hs>                                 -> example: java Esercizio2.java triplets.txt list 1 -
 * - listsize <hs>                         -> example: java Esercizio2.java triplets.txt listsize 1 -
 * - write (to make a file containing triplets) -> example: java Esercizio2.java triplets.txt write -
 * --------------------------------------------------------------------------------------------------
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
    private int n;
    private int m;

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

    public int getM() {
        return m;
    }
}

class HashDictionary<V> {
    HashTable<V> hashTable;

    public HashDictionary(int initialCapacity) {
        hashTable = new HashTable<>(initialCapacity);
    }

    // guard
    private boolean isValidHs(int hs) {
        if (hs < 0 || hs > hashTable.getM() - 1) {
            System.err.printf("* hs must be >= 0 and < %d !! *\n", hashTable.getM());
            return false;
        }
        return true;
    };

    public void insert(String key, V value, int hs) {
        if (!isValidHs(hs))
            return;
        hashTable.insert(key, value, hs);
    }

    public void lookup(String key, V value, int hs) {
        if (!isValidHs(hs))
            return;
        Entry<V> e = hashTable.lookup(key, hs);
        System.out
                .println(String.format("verifica presenza elemento: input <%s, %s, %d>; output ", key, value, hs)
                        + (e != null ? "‘elemento presente’"
                                : "‘elemento non presente’"));
    }

    public void printList(int hs) {
        if (!isValidHs(hs))
            return;
        String listString = hashTable.list(hs).toString();
        System.out.printf("stampa lista in base al valore di hs: input %d output %s\n", hs,
                listString.substring(1, listString.length() - 1));
    }

    public void printListSize(int hs) {
        if (!isValidHs(hs))
            return;
        System.out.printf("ricerca in base al valore hs: input %d; output %d\n", hs, hashTable.listSize(hs));
    }
}

public class Esercizio2 {
    private final static long seed = 900061028L;
    private final static int UPPER_BOUND = 100;
    private final static int LOWER_BOUND = 50;
    private final static int BIT_LENGTH = 5;
    public static final String LOOKUP = "lookup";
    public static final String LIST = "list";
    public static final String LISTSIZE = "listsize";
    public static final String WRITE = "write";

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

    public static void printError() {
        System.err.println("Usage: java Esercizio2 <file name> <operation> [operation options]");
        System.err.println("++++ [operation options] ++++");
        System.err.println("- lookup <key> <value> <hs>");
        System.err.println("- list <hs>");
        System.err.println("- listsize <hs>");
        System.err.println("- write");
        System.err.println("+++++++++++++++++++++++++++++");
        System.err.println("Please specify all arguments on the command line.");
    }

    public static void main(String[] args) {
        try {
            HashDictionary<Character> D = readFile(args[0]);
            final int hs;
            switch (args[1].toLowerCase()) {
                case LOOKUP:
                    final String key = args[2];
                    final char value = args[3].charAt(0);
                    hs = Integer.parseInt(args[4]);
                    D.lookup(key, value, hs);
                    break;
                case LIST:
                    hs = Integer.parseInt(args[2]);
                    D.printList(hs);
                    break;
                case LISTSIZE:
                    hs = Integer.parseInt(args[2]);
                    D.printListSize(hs);
                    break;
                case WRITE:
                    writeFile(args[0]);
                    break;
                default:
                    printError();
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            printError();
        } catch (NumberFormatException e) {
            System.err.println("hs must be an integer !!");
        }
        System.exit(1);
    }
}