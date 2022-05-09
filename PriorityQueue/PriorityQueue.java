package PriorityQueue;

/**
 * PriorityQueue
 */
public class PriorityQueue<T> {
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
            /**
             * Inserito in coda, ma l'elemento può avere priorità minore di quella del padre
             * Effettuo swap tra i e padre di i se la priorità è < della priorità del padre
             */
            while (i > 0 && H[i].getPriority() < H[p(i)].getPriority()) {
                PriorityItem.swap(H, i, p(i));
                i = p(i);
            }
            return H[i];
        } else {
            throw new IllegalStateException("PriorityQueue is full");
        }
    }

    private static <T> void minHeapRestore(PriorityItem<T>[] arr, int i, int dim) {
        int l_i = 2 * i + 1;
        int r_i = 2 * i + 2;
        int min = i;
        if (l_i <= dim && arr[l_i].getPriority() < arr[min].getPriority())
            min = l_i;
        if (r_i <= dim && arr[r_i].getPriority() < arr[min].getPriority())
            min = r_i;
        if (i != min) {
            PriorityItem.swap(arr, i, min);
            minHeapRestore(arr, min, dim);
        }
    }

    public T deleteMin() {
        if (dim > 0) {
            PriorityItem.swap(H, 0, dim - 1);
            dim--;
            minHeapRestore(H, 0, dim - 1);
            return H[dim].getValue();
        } else {
            throw new IllegalStateException("PriorityQueue is empty");
        }
    }

    public T min() {
        if (dim > 0) {
            return H[0].getValue();
        } else {
            throw new IllegalStateException("PriorityQueue is empty");
        }
    }

    public void decrease(PriorityItem<T> x, int p) {
        if (p < x.getPriority()) {
            x.setPriority(p);
            int i = x.getPos();
            while (i > 0 && H[i].getPriority() < H[p(i)].getPriority()) {
                PriorityItem.swap(H, i, p(i));
                i = p(i);
            }
        }
    }

    public void printAll() {
        for (PriorityItem<T> priorityItem : H) {
            System.out.println("POS " + priorityItem.getPos() + " VALUE: " + priorityItem.getValue() + " PRIORITY: "
                    + priorityItem.getPriority());
        }
    }
}