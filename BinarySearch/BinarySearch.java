package BinarySearch;

/**
 * Binary Search
 */
public class BinarySearch {
    public int binarySearch(int[] arr, int v, int low, int high) {
        if (low > high) {
            return -1;
        } else {
            int m = (low + high) / 2;
            if (arr[m] == v) {
                return m;
            } else if (arr[m] < v) {
                return binarySearch(arr, v, m + 1, high);
            } else {
                return binarySearch(arr, v, low, m - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = { 22, 42, 57, 81, 95 };
        BinarySearch binarySearch = new BinarySearch();

        System.out.println(
                String.format("l'elemento si trova nella posizione: %s",
                        binarySearch.binarySearch(arr, 57, 0, arr.length - 1)));
    }
}