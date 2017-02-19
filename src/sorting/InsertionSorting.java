package sorting;

public class InsertionSorting extends Sorting {
    @Override
    public void sortInner(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int j = i;
            while (j > 0 && less(arr[j], arr[j - 1])) {
                swap(arr, j, j - 1);
                j--;
            }
        }
    }
}

