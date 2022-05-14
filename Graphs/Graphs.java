package Graphs;

import java.util.HashMap;

import Containers.ListQueue;
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

    /**
     * Only for undirected graphs
     */
    public static <K, V> boolean hasCycle(Graph<K, V> G) {
        HashMap<K, Boolean> visited = new HashMap<>();
        for (K u : G.Vertexes())
            visited.put(u, false);

        for (K u : G.Vertexes())
            if (!visited.get(u))
                if (hasCycleRec(G, u, null, visited))
                    return true;
        return false;
    }

    private static <K, V> boolean hasCycleRec(Graph<K, V> G, K u, K p, HashMap<K, Boolean> visited) {
        visited.put(u, true);
        for (K v : Sets.remove(G.adj(u), p))
            if (visited.get(v))
                return true;
            else if (hasCycleRec(G, v, u, visited))
                return true;
        return false;
    }

    /**
     * Only for directed graphs
     */
    public static <K, V> boolean hasDirectedGraphCycle(Graph<K, V> G) {
        SchemaDFS<K> S = new SchemaDFS<>();

        for (K u : G.Vertexes()) {
            S.discover.put(u, 0);
            S.finish.put(u, 0);
        }
        for (K u : G.Vertexes())
            if (S.discover.get(u) == 0)
                if (hasDirectedGraphCycleRec(G, u, S))
                    return true;
        return false;
    }

    private static <K, V> boolean hasDirectedGraphCycleRec(Graph<K, V> G, K u, SchemaDFS<K> S) {
        S.discover.put(u, ++S.time);
        for (K v : G.adj(u))
            if (S.discover.get(v) == 0) {
                if (hasDirectedGraphCycleRec(G, v, S))
                    return true;
            } else if (S.discover.get(u) > S.discover.get(v) && S.finish.get(v) == 0)
                return true;
        S.finish.put(u, ++S.time);
        return false;
    }
}
