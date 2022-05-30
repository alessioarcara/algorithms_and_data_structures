package Graphs;

import java.util.HashMap;

import Containers.ListQueue;
import Containers.ListStack;
import Hashing.Hash;
import PriorityQueue.PriorityItem;
import PriorityQueue.PriorityQueue;
import Sets.Sets;

/**
 * This class contains various methods for manipulating graphs
 * (such as visit and topologic sort).
 */
public class Graphs {

    public static <K, V> void bfs(Graph<K, V> G, K r) {
        ListQueue<K> Q = new ListQueue<>();
        Q.enqueue(r);
        HashMap<K, Boolean> visited = new HashMap<>();
        for (K u : G.Vertexes())
            visited.put(u, false);
        visited.put(r, true);
        while (!Q.isEmpty()) {
            K u = Q.dequeue();
            System.out.println("Visita vertice -> " + u);
            for (K v : G.adj(u)) {
                System.out.println("Visita arco -> " + "(" + u + "," + v + ")");
                if (!visited.get(v)) {
                    visited.put(v, true);
                    Q.enqueue(v);
                }
            }
        }
    }

    public static <K, V> HashMap<K, Integer> distance(Graph<K, V> G, K r, HashMap<K, K> parent) {
        ListQueue<K> Q = new ListQueue<>();
        Q.enqueue(r);
        HashMap<K, Integer> distance = new HashMap<>();
        for (K u : G.Vertexes())
            distance.put(u, Integer.MAX_VALUE);
        distance.put(r, 0);
        parent.put(r, null);
        while (!Q.isEmpty()) {
            K u = Q.dequeue();
            for (K v : G.adj(u)) {
                if (distance.get(v) == Integer.MAX_VALUE) {
                    distance.put(v, distance.get(u) + 1);
                    parent.put(v, u);
                    Q.enqueue(v);
                }
            }
        }
        return distance;
    }

    public static <K> void printPath(K r, K s, HashMap<K, K> parent) {
        if (r == s)
            System.out.println(s);
        else if (parent.get(s) == null)
            System.out.println("nessun cammino da r a s");
        else {
            printPath(r, parent.get(s), parent);
            System.out.println(s);
        }
    }

    public static <K, V> HashMap<K, Integer> allShortestPaths(Graph<K, V> G, K r) {
        ListQueue<K> Q = new ListQueue<>();
        Q.enqueue(r);
        HashMap<K, Boolean> visited = new HashMap<>();
        HashMap<K, Integer> paths = new HashMap<>();
        for (K u : G.Vertexes()) {
            visited.put(u, false);
            paths.put(u, 0);
        }
        visited.put(r, true);
        paths.put(r, 1);
        while (!Q.isEmpty()) {
            K u = Q.dequeue();
            for (K v : G.adj(u)) {
                paths.put(v, paths.get(v) + 1);
                if (!visited.get(v)) {
                    visited.put(v, true);
                    Q.enqueue(v);
                }
            }
        }
        return paths;
    }

    public static <K, V> void dfs(Graph<K, V> G, K u, HashMap<K, Boolean> visited) {
        visited.put(u, true);
        // pre-order
        System.out.println(u);
        for (K v : G.adj(u))
            if (!visited.get(v))
                dfs(G, v, visited);
        // post-order
    }

    /**
     * Only for undirected graphs
     */
    public static <K, V> HashMap<K, Integer> cc(Graph<K, V> G) {
        HashMap<K, Integer> id = new HashMap<>();
        for (K u : G.Vertexes())
            id.put(u, 0);
        int counter = 0;
        for (K u : G.Vertexes())
            if (id.get(u) == 0) {
                counter = counter + 1;
                ccdfs(G, counter, u, id);
            }
        return id;
    }

    private static <K, V> void ccdfs(Graph<K, V> G, int counter, K u, HashMap<K, Integer> id) {
        id.put(u, counter);
        for (K v : G.adj(u))
            if (id.get(v) == 0)
                ccdfs(G, counter, v, id);
    }

    public static <K, V> boolean hasCycle(Graph<K, V> G) {
        SchemaDFS<K> S = new SchemaDFS<>();

        for (K u : G.Vertexes()) {
            S.discover.put(u, 0);
            S.finish.put(u, 0);
        }
        for (K u : G.Vertexes())
            if (S.discover.get(u) == 0)
                if (hasCycleRec(G, u, null, S))
                    return true;
        return false;
    }

    private static <K, V> boolean hasCycleRec(Graph<K, V> G, K u, K p, SchemaDFS<K> S) {
        S.discover.put(u, ++S.time);
        for (K v : Sets.remove(G.adj(u), p))
            if (S.discover.get(v) == 0) {
                if (hasCycleRec(G, v, u, S))
                    return true;
                // arco all'indietro
            } else if (S.discover.get(u) > S.discover.get(v) && S.finish.get(v) == 0)
                return true;
        S.finish.put(u, ++S.time);
        return false;
    }

    public static <K, V> ListStack<K> topSort(Graph<K, V> G) {
        ListStack<K> S = new ListStack<>();
        HashMap<K, Boolean> visited = new HashMap<>();
        for (K u : G.Vertexes())
            visited.put(u, false);
        for (K u : G.Vertexes())
            if (!visited.get(u))
                tsDfs(G, u, visited, S);
        return S;
    }

    private static <K, V> void tsDfs(Graph<K, V> G, K u, HashMap<K, Boolean> visited, ListStack<K> S) {
        visited.put(u, true);
        for (K v : G.adj(u))
            if (!visited.get(v))
                tsDfs(G, v, visited, S);
        S.push(u);
    }

    /**
     * Algorithm to find strongly connected components of a directed graph
     */
    public static <K, V> HashMap<K, Integer> kosaraju(Graph<K, V> G) {
        ListStack<K> S = topSort(G);
        Graph<K, V> G_t = transpose(G);
        return scc(G_t, S);
    }

    private static <K, V> Graph<K, V> transpose(Graph<K, V> G) {
        Graph<K, V> G_t = new Graph<>();
        for (K u : G.Vertexes())
            G_t.insertNode(u);
        for (K u : G.Vertexes())
            for (K v : G.adj(u))
                G_t.insertEdge(v, u, G.getWeight(u, v));
        return G_t;
    }

    private static <K, V> HashMap<K, Integer> scc(Graph<K, V> G, ListStack<K> S) {
        HashMap<K, Integer> id = new HashMap<>();
        for (K u : G.Vertexes())
            id.put(u, 0);
        int counter = 0;
        for (K u : G.Vertexes())
            if (id.get(u) == 0) {
                counter = counter + 1;
                ccdfs(G, counter, u, id);
            }
        return id;
    }

    public static <K, V> HashMap<K, K> prim(Graph<K, V> G, K s) {
        final int n = G.Vertexes().size();

        PriorityQueue<K> Q = new PriorityQueue<>(n);
        HashMap<K, PriorityItem<K>> d = new HashMap<>(n);
        HashMap<K, K> p = new HashMap<>(n);

        for (K u : Sets.remove(G.Vertexes(), s))
            d.put(u, Q.insert(u, Integer.MAX_VALUE));

        d.put(s, Q.insert(s, 0));
        p.put(s, s);

        while (!Q.isEmpty()) {
            K u = Q.deleteMin();
            d.put(u, null);
            for (K v : G.adj(u)) {
                if (d.get(v) != null && (int) G.getWeight(u, v) < d.get(v).getPriority()) {
                    Q.decrease(d.get(v), (int) G.getWeight(u, v));
                    p.put(v, u);
                }
            }
        }
        return p;
    }

    public static <K, V> HashMap<K, K> bellmanFordMoore(Graph<K, V> G, K s) {
        final int n = G.Vertexes().size();

        HashMap<K, Integer> d = new HashMap<>(n);
        HashMap<K, K> T = new HashMap<>(n);
        HashMap<K, Boolean> b = new HashMap<>(n);

        for (K u : Sets.remove(G.Vertexes(), s)) {
            T.put(u, null);
            d.put(u, Integer.MAX_VALUE);
            b.put(u, false);
        }

        T.put(s, null);
        d.put(s, 0);
        b.put(s, true);

        ListQueue<K> Q = new ListQueue<>();
        Q.enqueue(s);

        while (!Q.isEmpty()) {
            K u = Q.dequeue();
            b.put(u, false);
            for (K v : G.adj(u)) {
                if (d.get(u) + (int) G.getWeight(u, v) < d.get(v)) {
                    if (!b.get(u)) {
                        Q.enqueue(v);
                        b.put(v, true);
                    }
                    T.put(v, u);
                    d.put(v, d.get(u) + (int) G.getWeight(u, v));
                }
            }
        }

        return T;
    }

    public static <K, V> HashMap<K, PriorityItem<K>> dijkstra(Graph<K, V> G, K s) {
        final int n = G.Vertexes().size();

        HashMap<K, PriorityItem<K>> d = new HashMap<>(n);
        HashMap<K, K> T = new HashMap<>(n);
        HashMap<K, Boolean> b = new HashMap<>(n);

        PriorityQueue<K> Q = new PriorityQueue<>(n);

        for (K u : G.Vertexes()) {
            if (u == s) {
                d.put(u, Q.insert(u, 0));
                T.put(s, s);
            } else {
                d.put(u, Q.insert(u, Integer.MAX_VALUE));
                b.put(u, false);
            }
        }

        while (!Q.isEmpty()) {
            K u = Q.deleteMin();
            b.put(u, true);
            for (K v : G.adj(u)) {
                final int sum = d.get(u).getPriority() + (int) G.getWeight(u, v);
                if (!b.get(v) && sum < d.get(v).getPriority()) {
                    Q.decrease(d.get(v), sum);
                    T.put(v, u);
                }
            }
        }
        return d;
    }
}