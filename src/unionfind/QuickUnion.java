package unionfind;

public class QuickUnion implements UnionFind {
    private int[] arr;

    public QuickUnion(int n) {
        arr = new int[n];
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
        if (rootP != rootQ) {
            arr[rootP] = rootQ;
        }

    }

    private int findRoot(int p) {
       while (p != arr[p]) {
           p = arr[p];
       }
        return p;
//
//        if (arr[p] == p) {
//            return p;
//        } else {
//            return findRoot(arr[p]);
//        }
    }
}
