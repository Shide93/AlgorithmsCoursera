package unionfind;

public class QuickFind implements UnionFind {
    private int[] arr;

    public QuickFind(int n) {
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return arr[p] == arr[q];
    }

    @Override
    public void union(int p, int q) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == arr[q]) {
                arr[i] = arr[p];
            }
        }

    }
}
