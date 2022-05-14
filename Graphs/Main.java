package Graphs;

import java.util.HashMap;

public class Main {
    /**
     * InnerMain
     */
    private static class Pair {
        private String first;
        private String second;

        private Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        public String getFirst() {
            return first;
        }

        public String getSecond() {
            return second;
        }
    }

    public static void main(String[] args) {
        Graph<String, Integer> graph = new Graph<>();
        Graph<String, Integer> graph2 = new Graph<>();

        Pair[] edges = {
                new Pair("a", "b"),
                new Pair("a", "d"),
                new Pair("b", "c"),
                new Pair("d", "a"),
                new Pair("d", "c"),
                new Pair("d", "e"),
                new Pair("e", "c")
        };

        for (Pair pair : edges) {
            graph.insertEdge(pair.getFirst(), pair.getSecond(), 0);
        }

        graph.insertNode("f");
        graph.insertEdge("f", "v", 0);

        graph2.insertEdge("a", "b", 0);
        graph2.insertEdge("b", "a", 0);
        graph2.insertEdge("b", "c", 0);
        graph2.insertEdge("c", "b", 0);
        graph2.insertEdge("a", "c", 0);
        // graph2.insertEdge("c", "b", 0);

        for (String u : graph.Vertexes()) {
            System.out.println(u + " -> " + graph.adj(u).toString());
        }

        System.out.println("");
        Graphs.bfs(graph, "a");
        System.out.println("");

        HashMap<String, String> parent = new HashMap<>();
        System.out.println("Distanza di ogni nodo da 'c': " + Graphs.distance(graph, "b", parent).toString());

        Graphs.printPath("a", "e", parent);

        HashMap<String, Boolean> visited = new HashMap<>();
        for (String u : graph.Vertexes()) {
            visited.put(u, false);
        }
        Graphs.dfs(graph, "a", visited);

        System.out.println(Graphs.cc(graph).toString());

        System.out.println("Il grafo ha un ciclo: " + Graphs.hasCycle(graph));
    }
}
