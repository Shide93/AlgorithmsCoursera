package sorting;

public class SelectionSorting extends Sorting {
    @Override
    public void sortInner(Comparable[] arr) {

        for (int i = 0; i < arr.length; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (less(arr[j], arr[minIdx])) {
                    minIdx = j;
                }
            }
            swap(arr, i, minIdx);
        }
    }
}
