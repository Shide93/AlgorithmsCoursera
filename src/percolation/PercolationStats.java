package percolation;

import percolation.Percolation;

public class PercolationStats {

    private int n;
    private int trials;
    private Percolation percolation;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.trials = trials;
        this.percolation = new Percolation(n);
    }

    // test client (described below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);


        double mean = 0;
        double stddev = 0;
        double confLo = 0;
        double confHi = 0;
        System.out.println("mean                    = " + mean);
        System.out.println("stddev                  = " + stddev);
        System.out.println("95% confidence interval = " + confLo + " , " + confHi);
    }

//    // sample mean of percolation threshold
//    public double mean() {
//        return StdStats.mean();
//    }
//
//    // sample standard deviation of percolation threshold
//    public double stddev() {
//
//        return StdStats.stddev();
//    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return 0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {

        return 0;
    }
}