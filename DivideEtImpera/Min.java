package DivideEtImpera;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

// parallel divide and conquer min
public class Min extends RecursiveTask<Integer> {
    private int[] arr;
    private int start, end;

    public Min(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (start == end) {
            return arr[start];
        } else {
            int middle = (start + end) / 2;
            Min left = new Min(arr, start, middle);
            Min right = new Min(arr, middle + 1, end);
            invokeAll(left, right);
            return Math.min(left.join(), right.join());
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        int[] A = new int[n];

        for (int i = 0; i < A.length; i++) {
            A[i] = n - i;
        }

        ForkJoinPool pool = ForkJoinPool.commonPool();
        Min task = new Min(A, 0, n - 1);
        Integer result = pool.invoke(task);

        System.out.println(result);
    }
}
