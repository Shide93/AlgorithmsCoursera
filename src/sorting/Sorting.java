package sorting;

public abstract class Sorting {

    private long swapCount = 0;
    private long compareCount = 0;
    private long time;

    protected boolean less(Comparable v, Comparable w) {
        compareCount++;
        return v.compareTo(w) < 0;
    }

    protected void swap(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        swapCount++;
    }

    private boolean isSorted(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (less(arr[i], arr[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public void printStats() {
        System.out.println("Swaps: "+swapCount);
        swapCount = 0;
        System.out.println("Compares: "+compareCount);
        compareCount = 0;
        System.out.println("exec time: " + (System.currentTimeMillis() - time));
        time = 0;
    }
    public void sort(Comparable[] arr) {
        time = System.currentTimeMillis();
        sortInner(arr);
        if (!isSorted(arr)){
            for (Comparable c : arr) {
                System.out.print(c + ", ");
            }
            throw new RuntimeException("Not sorted!");
        }
    }

    protected abstract void sortInner(Comparable[] arr);
}
