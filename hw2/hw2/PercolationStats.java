package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;


public class PercolationStats {
    private double[] threshould;
    private int T;
    private int N;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N < 1 || T < 1) {
            throw new IllegalArgumentException();
        }
        threshould = new double[T];
        this.T = T;
        this.N = N;
        for (int i = 0; i < T; i += 1) {
            Percolation temp = pf.make(N);
            for (int j = 0; j < N * N; j += 1) {
                int rx = StdRandom.uniform(N);
                int ry = StdRandom.uniform(N);
                while (temp.isOpen(rx, ry)) {
                    rx = StdRandom.uniform(N);
                    ry = StdRandom.uniform(N);
                }
                temp.open(rx, ry);
                if (temp.percolates()) {
                    //System.out.println(j);
                    threshould[i] = (double) (j + 1) / (double) (N * N);
                    break;
                }
            }
        }

    }  // perform T independent experiments on an N-by-N grid
    public double mean() {
        return StdStats.mean(threshould);
    }                                          // sample mean of percolation threshold
    public double stddev() {
        return StdStats.stddev(threshould);
    }                                        // sample standard deviation of percolation threshold
    public double confidenceLow() {
        return mean() - (1.96 * Math.sqrt(stddev() / T));
    } // low endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + (1.96 * Math.sqrt(stddev() / T));
    }
}

