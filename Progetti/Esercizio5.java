
/**
 * Arcara Alessio
 * 0900061028
 * alessio.arcara@studio.unibo.it
 * 
 * È stata implementata una PriorityQueue, al posto di usare la struttura dati già presente
 * nella JDK, poichè si aggiunge l'operazione decrease(), che permette di decrementare la priorità
 * di un elemento con costo O(log n), invece che rimuovere e reinserire l'elemento con priorità
 * modificata nella struttura dati nella JDK con costo O(n + log n)=O(n).
 * 
 * Lista            Eseguite al max         Costo
 * ++++++++++++++++++++++++ +++++++ +++++++++++++
 * + insert ->    O(log n)+ + O(n)+ = O(n log n)+
 * + find ->          O(1)+ + O(n)+ =       O(n)+
 * + deleteMin -> O(log n)+ + O(n)+ = O(n log n)+
 * + decrease ->  O(log n)+ + O(m)+ = O(m log n)+
 * ++++++++++++++++++++++++ +++++++ +++++++++++++
 * Costo totale: O(2log n + n + mlog n)= O(m log n)
 * 
 * To compile: javac Esercizio5.java
 * To execute: java Esercizio5
 */
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;
import java.util.Stack;

class PriorityItem {
    private double priority;
    private int value;
    private int pos;

    public PriorityItem(double priority, int value, int pos) {
        this.priority = priority;
        this.value = value;
        this.pos = pos;
    }

    public static <T> void swap(PriorityItem[] H, int i, int j) {
        PriorityItem temp = H[i];
        H[i] = H[j];
        H[j] = temp;
        H[i].pos = i;
        H[j].pos = j;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public int getPos() {
        return pos;
    }

    public int getValue() {
        return value;
    }
}

class PriorityQueue {
    private int capacity;
    private int dim;
    private PriorityItem[] H;

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

    public boolean isEmpty() {
        return dim == 0;
    }

    public PriorityItem insert(int x, double p) {
        if (dim < capacity) {
            H[dim] = new PriorityItem(p, x, dim);
            int i = dim;
            dim = dim + 1;
            while (i > 0 && H[i].getPriority() < H[p(i)].getPriority()) {
                PriorityItem.swap(H, i, p(i));
                i = p(i);
            }
            return H[i];
        } else {
            throw new IllegalStateException("PriorityQueue is full");
        }
    }

    private static <T> void minHeapRestore(PriorityItem[] arr, int i, int dim) {
        final int l_i = 2 * i + 1;
        final int r_i = 2 * i + 2;
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

    public int deleteMin() {
        if (dim > 0) {
            PriorityItem.swap(H, 0, dim - 1);
            dim--;
            minHeapRestore(H, 0, dim - 1);
            return H[dim].getValue();
        } else {
            throw new IllegalStateException("PriorityQueue is empty");
        }
    }

    public void decrease(PriorityItem x, double p) {
        if (p < x.getPriority()) {
            x.setPriority(p);
            int i = x.getPos();
            while (i > 0 && H[i].getPriority() < H[p(i)].getPriority()) {
                PriorityItem.swap(H, i, p(i));
                i = p(i);
            }
        }
    }
}

class ShortestPath {
    int src;
    int n;
    ArrayList<LinkedList<Edge>> adjLists;
    int[] p;
    Edge[] sp;

    private class Edge {
        final int u;
        final int v;
        final double w;
        final char type;
        final double cost;

        public Edge(int u, int v, double w, char type, double cost) {
            assert (w >= 0.0);
            this.u = u;
            this.v = v;
            this.w = w;
            this.type = type;
            this.cost = cost;
        }
    }

    public ShortestPath(String inputf, int src) {
        this.src = src;
        readGraph(inputf);
    }

    private void readGraph(String inputf) {
        Locale.setDefault(Locale.US);

        try {
            Scanner f = new Scanner(new FileReader(inputf));
            n = f.nextInt();
            int m = f.nextInt();

            adjLists = new ArrayList<LinkedList<Edge>>(n);

            for (int i = 0; i < n; i++) {
                adjLists.add(i, new LinkedList<Edge>());
            }

            for (int i = 0; i < m; i++) {
                final char type = f.next().charAt(0);
                final int u = f.nextInt();
                final int v = f.nextInt();
                final double length = f.nextDouble();
                double cost = 0;

                if (type == 't') {
                    cost = f.nextDouble();
                }
                adjLists.get(u).add(new Edge(u, v, length, type, cost));
            }
        } catch (IOException ex) {
            System.err.println(ex);
            System.exit(1);
        }
    }

    private void dijkstra(int s, boolean byZipline) {
        sp = new Edge[n];
        PriorityItem[] d = new PriorityItem[n];
        p = new int[n];
        boolean[] visited = new boolean[n];
        PriorityQueue Q = new PriorityQueue(n);

        for (int v = 0; v < n; v++) {
            d[v] = Q.insert(v, (s == v ? 0.0 : Double.POSITIVE_INFINITY));
            p[v] = -1;
            visited[v] = false;
        }

        while (!Q.isEmpty()) {
            final int u = Q.deleteMin();
            visited[u] = true;
            for (Edge edge : adjLists.get(u)) {
                final int v = edge.v;
                final double sum = d[u].getPriority() + edge.w;
                if (byZipline || edge.type == 'p') {
                    if (!visited[v] && sum < d[v].getPriority()) {
                        Q.decrease(d[v], sum);
                        sp[v] = edge;
                        p[v] = u;
                    }
                }
            }
        }
    }

    public void printSP(int d, boolean byZipline) {
        dijkstra(src, byZipline);
        Stack<Edge> S = new Stack<>();
        int dst = d;
        while (src != dst) {
            S.add(sp[dst]);
            dst = p[dst];
        }
        double dtot = 0.0, ctot = 0.0;
        while (!S.isEmpty()) {
            final Edge e = S.pop();
            dtot += e.w;
            ctot += e.cost;
            System.out.printf("%s %d %d\n", e.type, e.u, e.v);
        }
        System.out.print(String.format("%.1f", dtot) + "\n" +
                (ctot != 0 ? (String.format("%.1f", ctot) + "\n") : ""));
    }
}

public class Esercizio5 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Esercizio5 <file name>");
            System.exit(1);
        }

        ShortestPath shortestPath = new ShortestPath(args[0], 0);
        shortestPath.printSP(6, false);
        shortestPath.printSP(6, true);
    }
}