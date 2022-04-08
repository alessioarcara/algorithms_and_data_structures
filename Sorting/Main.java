package Sorting;

interface Callable {
    void call();
}

public class Main {

    void calcTime(Callable func, String sortName) {
        long start_t, end_t, elapsed, min;
        double sec;
        start_t = System.currentTimeMillis();
        func.call();
        end_t = System.currentTimeMillis();
        elapsed = (end_t - start_t);
        min = elapsed / (60 * 1000);
        sec = (elapsed - min * 60 * 1000) / 1000.0;
        System.out.println(sortName + "Sort: " + min + " min " + sec + " sec");
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java main <list length>");
            System.exit(1);
        }

        int n = Integer.parseInt(args[0]);

        Main main = new Main();
        int[] A = new int[n];

        for (int i = 0; i < A.length; i++) {
            A[i] = n--;
        }

        main.calcTime(() -> QuickSort.sort(A), "Quick");
        main.calcTime(() -> MergeSort.sort(A), "Merge");
    }
}
