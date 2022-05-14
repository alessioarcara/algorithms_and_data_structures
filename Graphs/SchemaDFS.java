package Graphs;

import java.util.HashMap;

public class SchemaDFS<K> {
    HashMap<K, Integer> discover, finish;
    int time;

    public SchemaDFS() {
        this.discover = new HashMap<>();
        this.finish = new HashMap<>();
        this.time = 0;
    }
}
