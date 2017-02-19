import unionfind.QuickFind;
import unionfind.UnionFind;

public class Main {

    public static void main(String[] args) {
        int N = StdIn.readInt();
        UnionFind uf = new QuickFind(N);
        while (!StdIn.isEmpty())
        {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p, q))
            {
                uf.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
    }
}
