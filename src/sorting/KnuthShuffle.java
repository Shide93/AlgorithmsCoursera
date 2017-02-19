package sorting;

import edu.princeton.cs.algs4.StdRandom;

public class KnuthShuffle {
    public static void shuffle(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int r = StdRandom.uniform(i + 1);
            swap(arr, i, r);
        }
    }

    private static void swap(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
