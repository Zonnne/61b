package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;


public class PercolationStats {
    int[] threshould;
    int T;
    int N;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N < 0 || T < 0) {
            throw new IllegalArgumentException();
        }
        threshould = new int[T];
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
                    threshould[i] = j + 1;
                    break;
                }
            }
        }

    }  // perform T independent experiments on an N-by-N grid
    public double mean() {
        return StdStats.mean(threshould) / (double) (N * N);
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
    public static void main(String[] args) {
        PercolationStats h = new PercolationStats(40, 10000, new PercolationFactory());
        System.out.println(h.mean());
    }
}
