
/**
 * Arcara Alessio
 * 0900061028
 * alessio.arcara@studio.unibo.it
 * 
 * To compile: javac Esercizio1.java
 * To execute: java Esercizio1
*/
import java.util.Random;

class PriorityItem {
    private int priority;
    private final char value;

    public PriorityItem(int priority, char value) {
        this.priority = priority;
        this.value = value;
    }

    public static void swap(PriorityItem[] H, int i, int j) {
        PriorityItem temp = H[i];
        H[i] = H[j];
        H[j] = temp;
    }

    public int getPriority() {
        return priority;
    }

    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "<" + value + "," + priority + ">";
    }
}

class MaxPriorityQueue {
    private int capacity;
    private int dim;
    private PriorityItem[] H;

    public MaxPriorityQueue(int initialCapacity) {
        if (initialCapacity > 0) {
            this.capacity = initialCapacity;
            this.dim = 0;
            this.H = new PriorityItem[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity "
                    + initialCapacity);
        }
    }

    private int p(int i) {
        return (i - 1) / 2;
    }

    public PriorityItem insert(PriorityItem newItem) {
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

    public PriorityItem insertAtHead(PriorityItem newItem) {
        PriorityItem item = H[0];
        H[0] = newItem;
        maxHeapRestore(H, 0, dim - 1);
        return item;
    }

    private static void maxHeapRestore(PriorityItem[] arr, int i, int dim) {
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

    public PriorityItem deleteMax() {
        if (dim > 0) {
            PriorityItem.swap(H, 0, dim - 1);
            dim--;
            maxHeapRestore(H, 0, dim - 1);
            return H[dim];
        } else {
            throw new IllegalStateException("PriorityQueue is empty");
        }
    }

    public PriorityItem max() {
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
    private final static long seed = 900061028L;
    private final Random random;
    private final static int UPPER_LIMIT = 100;
    private final static int LOWER_LIMIT = 1;

    public Sensor() {
        random = new Random(seed);
    }

    public PriorityItem generateData(boolean verbose) {
        final char newInfo = (char) (random.nextInt('z' - 'a') + 'a');
        final int newPriority = random.nextInt(UPPER_LIMIT) + LOWER_LIMIT;
        if (verbose)
            System.out.printf("viene generata la coppia: <%s, %s>\n", newInfo, newPriority);
        return new PriorityItem(newPriority, newInfo);
    }

    public long getSeed() {
        return seed;
    }
}

public class Esercizio1 {
    // 8 + 8 ultima cifra matricola
    final static int K = 16;

    public static void main(String[] args) {
        MaxPriorityQueue S = new MaxPriorityQueue(K);
        Sensor sensor = new Sensor();

        for (int i = 0; i < K; i++)
            S.insert(sensor.generateData(false));

        System.out.println(String.format("K=%s, seme=%s;", K, sensor.getSeed()));
        S.printState();
        S.insertAtHead(sensor.generateData(true));
        S.printState();
    }
}
