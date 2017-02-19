package sorting;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Selects k-th largest element of array.
 */
public class Select {
    public Comparable select(Comparable[] arr, int k) {
        StdRandom.shuffle(arr);
        int lo = 0, hi = arr.length - 1;
        while (hi > lo) {
            int j = partition(arr, lo, hi);
            if (j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                return arr[k];
            }
        }
        return arr[k];
    }

    private int partition(Comparable[] arr, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (arr[++i].compareTo(arr[lo]) < 0) {

                if (i == hi) {
                    break;
                }
            }
            while (arr[lo].compareTo(arr[--j]) < 0) {

                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }

            swap(arr, i, j);
        }
        swap(arr, lo, j);
        return j;
    }

    protected void swap(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
