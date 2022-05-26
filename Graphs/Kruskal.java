package Graphs;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Kruskal {
    Edge[] E; // Array of edges of the input graph
    Edge[] T; // Array of edges that belong to the MST
    int n; // number of nodes
    int m; // number of edges
    double wtot;

    private class QuickUnion {
        private int[] p;
        private int[] rank;

        public QuickUnion(int initialCapacity) {
            if (initialCapacity > 0) {
                // ogni elemento punta se stesso -> n insiemi disgiunti
                p = new int[initialCapacity];
                rank = new int[initialCapacity];
                for (int i = 0; i < initialCapacity; i++) {
                    p[i] = i;
                    rank[i] = 0;
                }
            } else {
                throw new IllegalArgumentException("Illegal Capacity "
                        + initialCapacity);
            }
        }

        public void merge(int x, int y) {
            // rappresentanti di x e y
            int r_x = find(x);
            int r_y = find(y);
            if (r_x != r_y) {
                if (rank[r_x] > rank[y]) {
                    p[r_y] = r_x;
                } else if (rank[r_y] > rank[x]) {
                    p[r_x] = r_y;
                } else {
                    p[r_x] = r_y;
                    rank[r_y] = rank[y] + 1;
                }
            }
        }

        public int find(int x) {
            if (p[x] != x) {
                p[x] = find(p[x]);
            }
            return p[x];
        }
    }

    private class Edge implements Comparable {
        public final int u;
        public final int v;
        public final double w;

        public Edge(int u, int v, double w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        public int compareTo(Object other) {
            if (other == this) {
                return 0;
            } else {
                if (this.w > ((Edge) other).w) {
                    return 1;
                } else if (this.w == ((Edge) other).w) {
                    return 0;
                } else {
                    return -1;
                }
            }
        }
    }

    public Kruskal(String inputf) {
        readGraph(inputf);
        MST();
    }

    private void readGraph(String inputf) {
        Locale.setDefault(Locale.US);

        try {
            Scanner f = new Scanner(new FileReader(inputf));
            n = f.nextInt();
            m = f.nextInt();
            E = new Edge[m];

            for (int i = 0; i < m; i++) {
                final int u = f.nextInt();
                final int v = f.nextInt();
                final double w = f.nextDouble();
                E[i] = new Edge(u, v, w);
            }
        } catch (IOException ex) {
            System.err.println(ex);
            System.exit(1);
        }
    }

    private Edge[] MST() {
        T = new Edge[n - 1];
        QuickUnion M = new QuickUnion(n);
        int count = 0;
        int i = 1;

        Arrays.sort(E);
        wtot = 0.0;

        while (count < n - 1 && i <= m) {
            final int u = E[i].u;
            final int v = E[i].v;
            final double w = E[i].w;
            if (M.find(u) != M.find(v)) {
                M.merge(i, v);
                T[count++] = E[i];
                wtot += w;
            }
            i++;
        }
        return T;
    }

    public void dump() {
        System.out.printf("%d %d\n", n, n - 1); // the MST has n nodes and (n.1) edges
        for (int i = 0; i < T.length; i++) {
            final Edge e = T[i];
            System.out.printf("%d %d %f\n", e.u, e.v, e.w);
        }
        System.out.printf("# MST weight = %f\n", wtot);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            // System.err.println("Usage: java main <list length>");
            System.exit(1);
        }

        Kruskal mst = new Kruskal(args[0]);
        mst.dump();
    }
}
