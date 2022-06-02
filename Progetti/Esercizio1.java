
/**
 * Arcara Alessio
 * 0900061028
 * alessio.arcara@studio.unibo.it
 * 
 * To compile: javac Esercizio1.java
 * To execute: java Esercizio1
*/
import java.util.Random;

class PriorityItem<T> {
    private int priority;
    private T value;
    private int pos;

    public PriorityItem(int priority, T value, int pos) {
        this.priority = priority;
        this.value = value;
        this.pos = pos;
    }

    public static <T> void swap(PriorityItem<T>[] H, int i, int j) {
        PriorityItem<T> temp = H[i];
        H[i] = H[j];
        H[j] = temp;
        H[i].pos = i;
        H[j].pos = j;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPos() {
        return pos;
    }

    public T getValue() {
        return value;
    }
}

class PriorityQueue<T> {
    private int capacity;
    private int dim;
    private PriorityItem<T>[] H;

    @SuppressWarnings("unchecked")
    public PriorityQueue(int initialCapacity) {
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

    public PriorityItem<T> insert(T x, int p) {
        if (dim < capacity) {
            H[dim] = new PriorityItem<T>(p, x, dim);
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

    public T deleteMax() {
        if (dim > 0) {
            PriorityItem.swap(H, 0, dim - 1);
            dim--;
            maxHeapRestore(H, 0, dim - 1);
            return H[dim].getValue();
        } else {
            throw new IllegalStateException("PriorityQueue is empty");
        }
    }

    public T max() {
        if (dim > 0) {
            return H[0].getValue();
        } else {
            throw new IllegalStateException("PriorityQueue is empty");
        }
    }

    public void printAll() {
        StringBuffer sb = new StringBuffer();
        for (PriorityItem<T> priorityItem : H)
            sb.append("<" + priorityItem.getValue() + ", " + priorityItem.getPriority() + ">, ");
        System.out.println(sb.substring(0, sb.length() - 2).toString());
    }
}

class Sensor {

    final Random random;
    final long seed = 3131123;

    public class Data {
        private char info;
        private int priority;

        private Data(char info, int priority) {
            this.info = info;
            this.priority = priority;
        }

        public char getInfo() {
            return info;
        }

        public void setInfo(char info) {
            this.info = info;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }
    }

    public Sensor() {
        random = new Random(seed);
    }

    public Data generateData(boolean verbose) {
        char newInfo = (char) (random.nextInt('z' - 'a') + 'a');
        int newPriority = random.nextInt(100);
        if (verbose)
            System.out.println(String.format("Viene generata la coppia: <%s, %s>", newInfo, newPriority));
        return new Data(newInfo, newPriority);
    }

    public long getSeed() {
        return seed;
    }
}

public class Esercizio1 {

    // 8 + 8 ultima cifra matricola
    final static int K = 16;

    public static void main(String[] args) {
        PriorityQueue<Character> S = new PriorityQueue<>(K);
        Sensor sensor = new Sensor();

        for (int i = 0; i < K; i++) {
            Sensor.Data data = sensor.generateData(false);
            S.insert(data.getInfo(), data.getPriority());
        }

        System.out.println(String.format("K=%s, seme=%s", K, sensor.getSeed()));
        S.printAll();
        S.deleteMax();
        Sensor.Data data = sensor.generateData(true);
        S.insert(data.getInfo(), data.getPriority());
        S.printAll();
    }
}
