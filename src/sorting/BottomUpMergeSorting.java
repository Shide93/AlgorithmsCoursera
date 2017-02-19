package sorting;

public class BottomUpMergeSorting extends Sorting {
    private Comparable[] helper;
    private Comparable[] arr;

    @Override
    protected void sortInner(Comparable[] arr) {
        this.arr = arr;
        helper = new Comparable[arr.length];
        for (int size = 1; size < arr.length; size *= 2) {
            for (int lo = 0; lo < arr.length - size; lo += size * 2) {
                merge(lo, lo + size - 1, Math.min(lo + size * 2 - 1, arr.length - 1));
            }
        }
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
