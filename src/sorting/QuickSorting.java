package sorting;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdRandom;

public class QuickSorting extends Sorting {
    @Override
    protected void sortInner(Comparable[] arr) {

        StdRandom.shuffle(arr);
        sort(arr, 0, arr.length - 1);
    }

    private void sort(Comparable[] arr, int lo, int hi) {
        int cutoff = 10;
//        if (hi <= lo + cutoff - 1) {
//            Insertion.sort(arr, lo, hi);
//            return;
//        }
        if (hi <= lo) {
            return;
        }
//         int m = medianOf3(arr,lo,lo+(hi-lo)/2,hi);
//        swap(arr,lo,m);
        int j = partition(arr, lo, hi);
        sort(arr, lo, j - 1);
        sort(arr, j + 1, hi);

    }

    private int partition(Comparable[] arr, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (less(arr[++i], arr[lo])) {

                if (i == hi) {
                    break;
                }
            }
            while (less(arr[lo], arr[--j])) {

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
}
