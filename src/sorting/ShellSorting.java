package sorting;

public class ShellSorting extends Sorting {
    @Override
    public void sortInner(Comparable[] arr) {
        int h = getMaxH(arr);

        while (h >= 1) {
            for (int i = h; i < arr.length; i++) {
                for (int j = i; j >= h && less(arr[j], arr[j - h]); j -= h) {
                    swap(arr, j, j - h);
                }
            }
            h = h/3;
        }
    }

    private int getMaxH(Comparable[] arr) {
        int h = 1;
        while (h < arr.length / 3) {
            h = h * 3 + 1;      // 1, 4, 13, 40, 121, 364, ...      Knuth's formula
        }
        return h;
    }
}
