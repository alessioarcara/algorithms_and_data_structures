package Sorting;

import java.util.Random;

public class QuickSort {
    private static void swap(int arr[], int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static void shuffle(int arr[]) {
        Random rnd = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            swap(arr, i, j);
        }
    }

    public static void sort(int arr[]) {
        shuffle(arr);
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int arr[], int l, int r) {
        int p;
        if (l < r) {
            p = pivot(arr, l, r);
            quickSort(arr, l, p - 1);
            quickSort(arr, p + 1, r);
        }
    }

    private static int pivot(int arr[], int l, int r) {
        int p = l;
        int j = l;
        for (int i = l; i < r; i++) {
            if (arr[i] < arr[p]) {
                j++;
                swap(arr, i, j);
            }
        }
        swap(arr, p, j);
        return j;
    }
}
