package sorting;

import java.util.Arrays;

public class MergeSorting extends Sorting {

    private Comparable[] helper;
    private Comparable[] arr;

    @Override
    protected void sortInner(Comparable[] arr) {
        helper = new Comparable[arr.length];
        this.arr = arr;
        sort(0, arr.length - 1);
    }

    private void sort(int lo, int hi) {
        if (lo >= hi) return;
        int cutoff = 7;
//        if (hi <= lo + cutoff - 1) {
            //INSertion sort of subarry
            //+20% performance
//        }
        int mid = lo + (hi - lo) / 2;

        sort(lo, mid);
        sort(mid + 1, hi);

        if (!less(arr[mid+1], arr[mid])) return;        //helps if partially sorted

        merge(lo, mid, hi);
    }

    private void merge(int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            helper[i] = arr[i];
        }

        int i = lo;     // first half pointer
        int j = mid + 1;  // second half pointer
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {                  // first half array is exhausted
                arr[k] = helper[j++];
            } else if (j > hi) {            // second half array is exhausted
                arr[k] = helper[i++];
            } else if (less(helper[j], helper[i])) {
                arr[k] = helper[j++];
            } else {
                arr[k] = helper[i++];
            }
        }
    }
}
