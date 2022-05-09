package Sorting;

import Utils.Utils;

public class HeapSort {
    public static void sort(int arr[]) {
        int n = arr.length - 1;
        heapBuild(arr, n);
        for (int i = n; i >= 1; i--) {
            Utils.swap(arr, 0, i);
            maxHeapRestore(arr, 0, i - 1);
        }
    }

    private static void heapBuild(int[] arr, int n) {
        for (int i = n / 2; i >= 0; i--) {
            maxHeapRestore(arr, i, n);
        }
    }

    private static void maxHeapRestore(int[] arr, int i, int dim) {
        int l_i = 2 * i + 1;
        int r_i = 2 * i + 2;
        int max = i;
        if (l_i <= dim && arr[l_i] > arr[max])
            max = l_i;
        if (r_i <= dim && arr[r_i] > arr[max])
            max = r_i;
        if (i != max) {
            Utils.swap(arr, i, max);
            maxHeapRestore(arr, max, dim);
        }
    }
}
