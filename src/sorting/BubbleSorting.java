package sorting;

public class BubbleSorting extends Sorting {
    @Override
    public void sortInner(Comparable[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 1; j < arr.length-i; j++) {
                if (less(arr[j], arr[j-1])) {
                    swap(arr, j-1, j);
                }
            }
        }

    }
}
