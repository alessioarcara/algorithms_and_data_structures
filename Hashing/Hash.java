package Hashing;

/**
 * Hash Table Open Addressing, Ispezione Quadratica
 */
public class Hash<V> {
    private String[] kTable;
    private V[] vTable;
    int m;

    @SuppressWarnings("unchecked")
    public Hash(int initialCapacity) {
        if (initialCapacity > 0) {
            this.m = initialCapacity;
            this.kTable = new String[initialCapacity];
            this.vTable = (V[]) new Object[initialCapacity];

            for (int i = 0; i < m; i++) {
                kTable[i] = null;
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

    private int scan(String k, boolean insert) {
        // sentinella che indica se si è incontrato un elemento deleted tra 0 e m-1
        int delpos = m;
        int i = 0; // numero di ispezione
        int j = hash(k); // posizione attuale
        /**
         * esce
         * se 1) la chiave è trovata
         * se 2) la chiave è null
         * se 3) è stata ispezionata tutta la tabella
         */
        while (kTable[j] != k && kTable[j] != null && i < m) {
            if (kTable[j] == "deleted" && delpos == m) {
                delpos = j;
            }
            j = (j + (int) Math.pow(i, 2)) % m;
            i = i + 1;
        }
        if (insert && kTable[j] != k && delpos < m) {
            return delpos;
        } else {
            return j;
        }
    }

    public V lookup(String k) {
        int i = scan(k, false);
        if (kTable[i] == k) {
            return vTable[i];
        } else {
            return null;
        }
    }

    public void insert(String k, V v) {
        int i = scan(k, true);
        if (kTable[i] == null || kTable[i] == "deleted" || kTable[i] == k) {
            kTable[i] = k;
            vTable[i] = v;
        } else {
            throw new IllegalStateException("Table overflow");
        }
    }

    public void remove(String k) {
        int i = scan(k, false);
        if (kTable[i] == k) {
            kTable[i] = "deleted";
            vTable[i] = null;
        }
    }

    public void printAll() {
        for (int i = 0; i < m; i++) {
            if (kTable[i] != null && kTable[i] != "deleted") {
                System.out.println("KEY: " + kTable[i] + " VALUE: " + vTable[i]);
            }
        }
    }
}
