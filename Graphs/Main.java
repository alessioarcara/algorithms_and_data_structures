package Graphs;

import java.util.HashMap;

import Containers.ListStack;
import PriorityQueue.PriorityItem;

public class Main {

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
        Graph<String, Integer> graph3 = new Graph<>();

        Pair[] edges = {
                new Pair("a", "b"),
                new Pair("a", "d"),
                new Pair("b", "c"),
                new Pair("c", "e"),
                new Pair("d", "c"),
                new Pair("e", "d"),
                new Pair("e", "f"),
                new Pair("f", "c"),

        };

        for (Pair pair : edges) {
            graph.insertEdge(pair.getFirst(), pair.getSecond(), 0);
        }

        graph2.insertEdge("a", "b", 4);
        graph2.insertEdge("b", "a", 4);
        graph2.insertEdge("b", "c", 4);
        graph2.insertEdge("c", "b", 16);
        graph2.insertEdge("c", "a", 20);
        graph2.insertEdge("a", "c", 20);

        graph3.insertEdge("a", "b", 0);
        graph3.insertEdge("a", "c", 0);
        graph3.insertEdge("b", "c", 0);
        graph3.insertEdge("c", "d", 0);
        graph3.insertEdge("b", "e", 0);
        graph3.insertEdge("c", "e", 0);
        graph3.insertEdge("d", "e", 0);

        for (String u : graph.Vertexes()) {
            System.out.println(u + " -> " + graph.adj(u).toString());
        }

        /**
         * BFS
         */
        System.out.println("");
        Graphs.bfs(graph, "a");
        System.out.println("");

        /**
         * Distance
         */
        HashMap<String, String> parent = new HashMap<>();
        System.out.println("Distanza di ogni nodo da 'c': " + Graphs.distance(graph, "a", parent).toString());

        /**
         * Stampa cammino da x a y
         */
        Graphs.printPath("a", "e", parent);

        /**
         * DFS
         */
        HashMap<String, Boolean> visited = new HashMap<>();
        for (String u : graph.Vertexes()) {
            visited.put(u, false);
        }
        Graphs.dfs(graph, "a", visited);

        /**
         * Etichettatura delle componenti (fortemente) connesse
         */
        System.out.println("etichettatura con algoritmo cc: " + Graphs.cc(graph).toString());
        System.out.println("etichettatura con algoritmo di Kosaraju" + Graphs.kosaraju(graph).toString());

        /**
         * Grafo aciclico
         */
        System.out.println("Il grafo ha un ciclo: " + Graphs.hasCycle(graph));

        /**
         * Ordinamento topologico
         */
        if (!Graphs.hasCycle(graph)) {
            ListStack<String> S = Graphs.topSort(graph);
            System.out.print("L'ordine dei nodi: ");
            S.printAll();
        }

        /**
         * All shortest paths (Exercize)
         */
        System.out.println(Graphs.allShortestPaths(graph3, "a"));

        /**
         * Prim
         */

        System.out.println(Graphs.prim(graph2, "a").toString());

        /**
         * Bellman Ford
         */

        HashMap<String, PriorityItem<String>> distanceMap = Graphs.dijkstra(graph2, "a");
        for (PriorityItem<String> distance : distanceMap.values()) {
            System.out.println(distance.getPriority());
        }
    }
}
