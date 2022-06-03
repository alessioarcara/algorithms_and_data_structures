
/**
 * Arcara Alessio
 * 0900061028
 * alessio.arcara@studio.unibo.it
 * 
 * Algoritmo di Prim, implementato con una LinkedList al posto della coda con priorità,
 * ha complessità O(n^2). Per grafi densi (come quello dell'esercizio), m è Ω(n^2), 
 * la complessità risulta O(n^2 log n) e l'implementazione con coda con priorità è più lenta 
 * di quella proposta.
 * 
 * Inoltre, è stata implementata una LinkedList bidirezionale al posto di utilizzare 
 * la struttura dati già presente nella JDK, poichè migliora il costo dell'operazione remove()
 * da O(n) a O(1).
 * 
 * Lista              Eseguite al max
 * +++++++++++++++++++ ++++++++ +++++++++
 * + insert ->  O(1) + + O(n) + = O(n)  +
 * + find ->    O(n) + + O(n) + = O(n^2)+
 * + remove ->  O(1) + + O(n) + = O(n)  +
 * + if/elif -> O(1) + + O(m) + = O(m)  +
 * +++++++++++++++++++ ++++++++ +++++++++
 * Costo totale: O(n^2+2n+m)= O(m)<= O(n^2)
 * 
 * To compile: javac Esercizio3.java
 * To execute: java Esercizio3 <name file>
 */
import java.util.Locale;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Location {
    double x;
    double y;

    Location(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class Pair<K> {
    K u;
    double w;

    Pair(K u, double w) {
        this.u = u;
        this.w = w;
    }
}

class Node<T> {
    Node<T> succ;
    Node<T> pred;
    T value;

    Node(T value) {
        this.value = value;
        this.pred = this;
        this.succ = this;
    }
}

/**
 * List bidirectional, circular, with sentinel
 */
class LinkedList<T> {
    private Node<T> sentinel;

    LinkedList() {
        this.sentinel = new Node<>(null);
    }

    public Node<T> head() {
        return this.sentinel.succ;
    }

    public Node<T> tail() {
        return this.sentinel.pred;
    }

    public boolean isEmpty() {
        return (this.sentinel.succ == this.sentinel.pred) && (this.sentinel.succ == this.sentinel);
    }

    public boolean finished(Node<T> pos) {
        return this.sentinel == pos;
    }

    public Node<T> append(T value) {
        Node<T> t = new Node<>(value);
        t.pred = this.tail();
        t.succ = this.sentinel;
        this.tail().succ = t;
        this.sentinel.pred = t;
        return t;
    }

    public Node<T> remove(Node<T> pos) {
        pos.pred.succ = pos.succ;
        pos.succ.pred = pos.pred;
        Node<T> t = pos.succ;
        pos = null;
        return t;
    }
}

class MST {
    int n;
    ArrayList<LinkedList<Edge>> adjLists;
    Edge[] mst;
    double wtot;

    private class Edge {
        final int u;
        final int v;
        final double w;

        public Edge(int u, int v, double w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        public int opposite(int v) {
            assert ((u == v) || (this.v == v));

            return (v == u ? v : u);
        }
    }

    public MST(String inputf, int s) {
        this.wtot = 0.0;
        createCompleteGraph(inputf);
        prim(s);
    }

    private void createCompleteGraph(String inputf) {
        Locale.setDefault(Locale.US);

        try {
            Scanner f = new Scanner(new FileReader(inputf));
            n = f.nextInt();

            mst = new Edge[n];
            adjLists = new ArrayList<LinkedList<Edge>>(n);

            Location locations[] = new Location[n];
            for (int i = 0; i < n; i++) {
                final double x = f.nextDouble();
                final double y = f.nextDouble();
                locations[i] = new Location(x, y);
                adjLists.add(i, new LinkedList<Edge>());
            }

            for (int i = 0; i < locations.length; i++)
                for (int j = 0; j < locations.length; j++)
                    if (i != j) {
                        final double dist = Math.sqrt(Math.pow(locations[i].x - locations[j].x, 2)
                                + Math.pow(locations[i].y - locations[j].y, 2));
                        final Edge newEdge = new Edge(i, j, dist);
                        adjLists.get(i).append(newEdge);
                        adjLists.get(j).append(newEdge);
                    }
        } catch (IOException ex) {
            System.err.println(ex);
            System.exit(1);
        }
    }

    public static Integer deleteMin(LinkedList<Pair<Integer>> L) {
        Node<Pair<Integer>> node = L.head().succ;
        Node<Pair<Integer>> minNode = L.head();
        while (!L.finished(node)) {
            if (minNode.value.w > node.value.w)
                minNode = node;
            node = node.succ;
        }
        L.remove(minNode);
        return minNode.value.u;
    }

    private void prim(int s) {
        double[] d = new double[n];
        int[] p = new int[n];
        boolean[] added = new boolean[n];

        for (int i = 0; i < n; i++) {
            d[i] = Double.POSITIVE_INFINITY;
            p[i] = -1;
            added[i] = false;
        }

        d[s] = 0.0;
        wtot = 0.0;

        LinkedList<Pair<Integer>> L = new LinkedList<>();
        L.append(new Pair<>(s, 0));

        while (!L.isEmpty()) {
            int u = MST.deleteMin(L);
            added[u] = true;
            LinkedList<Edge> adjL = adjLists.get(u);
            Node<Edge> n = adjL.head();
            while (!adjL.finished(n)) {
                final Edge edge = n.value;
                final int v = edge.opposite(u);
                final double w = edge.w;
                if (!added[v])
                    if (d[v] == Double.POSITIVE_INFINITY) {
                        d[v] = w;
                        p[v] = u;
                        mst[v] = edge;
                        wtot += d[v];
                        L.append(new Pair<>(v, w));
                    } else if (w < d[v]) {
                        wtot -= d[v];
                        d[v] = w;
                        p[v] = u;
                        mst[v] = edge;
                        wtot += w;
                    }
                n = n.succ;
            }
        }
    }

    public void printMST() {
        for (int i = 0; i < n; i++) {
            final Edge e = mst[i];
            if (e != null)
                System.out.printf("%d %d %f\n", e.u, e.v, e.w);
        }
        System.out.println(wtot);
    }
}

public class Esercizio3 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Esercizio3 <file name>");
            System.exit(1);
        }

        MST mst = new MST(args[0], 3);
        mst.printMST();
    }
}