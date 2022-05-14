package Graphs;

import java.util.HashMap;
import java.util.Set;

public class Graph<K, V> {
    private HashMap<K, HashMap<K, V>> vertexes;

    public Graph() {
        vertexes = new HashMap<>();
    }

    public Set<K> Vertexes() {
        return vertexes.keySet();
    }

    public int size() {
        return vertexes.size();
    }

    public Set<K> adj(K u) {
        if (vertexes.containsKey(u))
            return vertexes.get(u).keySet();
        return null;
    }

    public void insertNode(K u) {
        if (!vertexes.containsKey(u))
            vertexes.put(u, new HashMap<>());
    }

    public void insertEdge(K u, K v, V w) {
        insertNode(u);
        insertNode(v);
        vertexes.get(u).put(v, w);
    }
}
