package Mfset;

public class Mfset {
    private int[] p;
    private int[] rank;

    public Mfset(int initialCapacity) {
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
