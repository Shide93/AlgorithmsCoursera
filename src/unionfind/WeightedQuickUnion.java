package unionfind;

public class WeightedQuickUnion implements UnionFind {
    private int[] arr;
    private int[] componentSize;

    public WeightedQuickUnion(int n) {
        arr = new int[n];
        componentSize = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    @Override
    public void union(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        if (rootP == rootQ) return;
        if (componentSize[rootP] < componentSize[rootQ]) {
            arr[rootP] = rootQ;
            componentSize[rootQ]+= componentSize[rootP];
        } else {
            arr[rootQ] = rootP;
            componentSize[rootP]+= componentSize[rootQ];
        }

    }

    private int findRoot(int p) {
        while (p != arr[p]) {
            p = arr[p];
        }
        return p;
    }
}
