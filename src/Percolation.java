import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 *
 */
public class Percolation {
    private final int N;
    private int gridSize;
    private int[] flatGrid;
    private WeightedQuickUnionUF uf;
    private int openedSitesCount;
    private int virtualTopIndex;
    private int virtualBottomIndex;

    // create gridSize-by-gridSize flatGrid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("The value is outside the range");
        }
        this.gridSize = n;
        flatGrid = new int[n * n];   //closed flatGrid

        //number of sites + 2 virtual
        N = n * n + 2;
        virtualTopIndex = N - 1;
        virtualBottomIndex = N - 2;
        uf = new WeightedQuickUnionUF(N); // N-1 is virtual top, N-2 is virtual bottom
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        int siteIdx = getSiteIndex(row, col);

        updateUF(siteIdx);
        //TODO: union first and last row

        if (uf.connected(siteIdx, virtualTopIndex)) {
            flatGrid[siteIdx] = 2;
        } else {
            flatGrid[siteIdx] = 1;
        }
        openedSitesCount++;
    }

    private int getSiteIndex(int row, int col) {
        checkGivenCoordinates(row, col);
        row--;
        col--;
        return row * gridSize + col;
    }

    private void updateUF(int ufIndex) {
        int topNeighb = ufIndex - gridSize;
        int botNeighb = ufIndex + gridSize;
        int leftNeighb = ufIndex - 1;
        int rightNeighb = ufIndex + 1;

        unionNeighbours(ufIndex, topNeighb);
        unionNeighbours(ufIndex, botNeighb);
        unionNeighbours(ufIndex, leftNeighb);
        unionNeighbours(ufIndex, rightNeighb);


    }

    private void unionNeighbours(int ufIndex, int neighbour) {
        if (flatGrid[neighbour] > 0) {
            uf.union(ufIndex, neighbour);
        }
        //TODO:
    }


    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        return flatGrid[getSiteIndex(row,col)] != 0;
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        return flatGrid[getSiteIndex(row,col)] == 2;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openedSitesCount;
    }


    // does the system percolate?
    public boolean percolates() {
        return uf.connected(virtualTopIndex, virtualBottomIndex);
    }

    private void checkGivenCoordinates(int row, int col) {
        if (row <= 0 || row > gridSize) {
            throw new IndexOutOfBoundsException("Row out of bound: Row - " + row + ", size - " +gridSize);
        }
        if (col <= 0 || col > gridSize) {
            throw new IndexOutOfBoundsException("Col out of bound: Col - " + col + ", size - " +gridSize);
        }
    }
}