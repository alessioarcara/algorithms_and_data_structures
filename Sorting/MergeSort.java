package Sorting;

import Containers.VectorQueue;

public class MergeSort {
    public static void sort(int arr[]) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private static void mergeSort(int arr[], int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, r, m);
        }
    }

    private static void merge(int arr[], int l, int r, int m) {
        int i = l;
        int j = m + 1;
        VectorQueue<Integer> queue = new VectorQueue<Integer>(r - l + 1);

        while (i <= m && j <= r) {
            if (arr[i] <= arr[j]) {
                queue.enqueue(arr[i++]);
            } else {
                queue.enqueue(arr[j++]);
            }
        }

        while (i <= m) {
            queue.enqueue(arr[i++]);
        }

        j = l;
        while (!queue.isEmpty()) {
            arr[j++] = queue.dequeue();
        }
    }
}
