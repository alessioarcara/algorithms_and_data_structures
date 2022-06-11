
/**
 * Arcara Alessio
 * 0900061028
 * alessio.arcara@studio.unibo.it
 * 
 * Come struttura ad accesso diretto è stata usata una coda con priorità, realizzata con uno
 * heap binario. Poichè la coppia avente la priorità più alta deve essere dapprima eliminata, 
 * viene usata l'operazione insertAtHead() per sovrascrivere tale coppia con una nuova coppia,
 * inserendo direttamente in testa e ripristinando successivamente le proprietà heap.
 * Così facendo, si riduce il massimo numero di confronti da 2*log n a log n.
 * 
 * PriorityQueue            
 * +++++++++++++++++++++++++++
 * + insert ->       O(log n)+
 * + insertAtHead -> O(log n)+
 * + max ->              O(1)+
 * + deleteMax ->    O(log n)+
 * +++++++++++++++++++++++++++
 * 
 * To compile: javac Esercizio1.java
 * To execute: java Esercizio1
*/
import java.util.Random;

class PriorityItem<T> {
    private int priority;
    private final T value;

    public PriorityItem(int priority, T value) {
        this.priority = priority;
        this.value = value;
    }

    public static <T> void swap(PriorityItem<T>[] H, int i, int j) {
        PriorityItem<T> temp = H[i];
        H[i] = H[j];
        H[j] = temp;
    }

    public int getPriority() {
        return priority;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "<" + value + "," + priority + ">";
    }
}

class MaxPriorityQueue<T> {
    private int capacity;
    private int dim;
    private PriorityItem<T>[] H;

    public MaxPriorityQueue(int initialCapacity) {
        if (initialCapacity > 0) {
            this.capacity = initialCapacity;
            this.dim = 0;
            @SuppressWarnings("unchecked")
            final PriorityItem<T>[] H = new PriorityItem[initialCapacity];
            this.H = H;
        } else {
            throw new IllegalArgumentException("Illegal Capacity "
                    + initialCapacity);
        }
    }

    private int p(int i) {
        return (i - 1) / 2;
    }

    public PriorityItem<T> insert(PriorityItem<T> newItem) {
        if (dim < capacity) {
            H[dim] = newItem;
            int i = dim;
            dim = dim + 1;
            while (i > 0 && H[i].getPriority() > H[p(i)].getPriority()) {
                PriorityItem.swap(H, i, p(i));
                i = p(i);
            }
            return H[i];
        } else {
            throw new IllegalStateException("PriorityQueue is full");
        }
    }

    public PriorityItem<T> insertAtHead(PriorityItem<T> newItem) {
        PriorityItem<T> oldItem = H[0];
        System.out.printf("viene eliminata la coppia: %s\n", oldItem.toString());
        H[0] = newItem;
        maxHeapRestore(H, 0, dim - 1);
        return oldItem;
    }

    private static <T> void maxHeapRestore(PriorityItem<T>[] arr, int i, int dim) {
        int l_i = 2 * i + 1;
        int r_i = 2 * i + 2;
        int max = i;
        if (l_i <= dim && arr[l_i].getPriority() > arr[max].getPriority())
            max = l_i;
        if (r_i <= dim && arr[r_i].getPriority() > arr[max].getPriority())
            max = r_i;
        if (i != max) {
            PriorityItem.swap(arr, i, max);
            maxHeapRestore(arr, max, dim);
        }
    }

    public PriorityItem<T> deleteMax() {
        if (dim > 0) {
            PriorityItem.swap(H, 0, dim - 1);
            dim--;
            maxHeapRestore(H, 0, dim - 1);
            return H[dim];
        } else {
            throw new IllegalStateException("PriorityQueue is empty");
        }
    }

    public PriorityItem<T> max() {
        if (dim > 0) {
            return H[0];
        } else {
            throw new IllegalStateException("PriorityQueue is empty");
        }
    }

    public void printState() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < dim; i++)
            sb.append(H[i] + (i < dim - 1 ? ", " : ""));
        System.out.println("S=" + sb);
    }
}

class Sensor {
    private final static int UPPER_BOUND = 100;
    private final static int LOWER_BOUND = 1;
    private Random random;
    private long seed;

    public Sensor(long seed) {
        this.seed = seed;
        random = new Random(seed);
    }

    public PriorityItem<Character> generateData(boolean verbose) {
        final char newInfo = (char) (random.nextInt('z' - 'a') + 'a');
        final int newPriority = random.nextInt((UPPER_BOUND - LOWER_BOUND) + 1) + LOWER_BOUND;
        if (verbose)
            System.out.printf("viene generata la coppia: <%s,%s>\n", newInfo, newPriority);
        return new PriorityItem<>(newPriority, newInfo);
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
        this.random.setSeed(seed);
    }
}

public class Esercizio1 {
    // 8 + 8 ultima cifra matricola
    final static int K = 16;

    public static void main(String[] args) {
        MaxPriorityQueue<Character> S = new MaxPriorityQueue<>(K);
        Sensor sensor = new Sensor(900061028L);

        // Popolamento struttura dati S
        for (int i = 0; i < K; i++)
            S.insert(sensor.generateData(false));
        System.out.println(String.format("K=%s, seme=%s;", K, sensor.getSeed()));
        S.printState();

        // K operazioni di cancellazione/inserimento
        sensor.setSeed(3131123L);
        for (int i = 0; i < K; i++)
            S.insertAtHead(sensor.generateData(true));
        S.printState();
    }
}
