package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF opened;
    private WeightedQuickUnionUF connectedU;
    private SquareObject[][] grid;
    private int N;
    private int counts;
    private class SquareObject {
        private boolean open;
        private int id;
        SquareObject(int x, int y, int n) {
            open = false;
            id = x * n + y; //hashcode && Binary
        }
    }

    public Percolation(int N) {
        counts = 0;
        if (N < 1) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        grid = new SquareObject[N][N];
        opened = new WeightedQuickUnionUF(N * N + 1);
        connectedU = new WeightedQuickUnionUF(N * N + 3);
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                grid[i][j] = new SquareObject(i, j, N);
            }
        }
//        for (int i = 0; i < N; i += 1) {
//            connectedU.union(grid[0][i].id, N * N + 1);
//            connectedU.union(grid[N - 1][i].id, N * N + 2);
//        }

    }                // create N-by-N grid, with all sites initially blocked
    public void open(int row, int col) {
        if (row >= grid.length || col >= grid.length) {
            throw new IndexOutOfBoundsException();
        }
        if (!grid[row][col].open) {
            counts += 1;
        }
        grid[row][col].open = true;

        if (row == 0) {
            opened.union(grid[row][col].id, N * N);
            connectedU.union(N * N, grid[row][col].id);
            if ((row + 1) < N) {
                connection(grid[row][col], grid[row + 1][col]);
            }
        }
        if (row == N - 1) {
            connectedU.union(N * N + 1, grid[row][col].id);
            if ((row - 1) >= 0) {
                connection(grid[row][col], grid[row - 1][col]);
            }
            if ((col + 1) < N) {
                connection(grid[row][col], grid[row][col + 1]);
            }
            if ((col - 1) >= 0) {
                connection(grid[row][col], grid[row][col - 1]);
            }
        } else {
            if ((row - 1 >= 0)) {
                connection(grid[row][col], grid[row - 1][col]);
            }
            if ((row + 1) < N) {
                connection(grid[row][col], grid[row + 1][col]);
            }
            if ((col + 1) < N) {
                connection(grid[row][col], grid[row][col + 1]);
            }
            if ((col - 1) >= 0) {
                connection(grid[row][col], grid[row][col - 1]);
            }
        }
    }      // open the site (row, col) if it is not open already
    private void connection(SquareObject center, SquareObject neighbour) {
        if (neighbour.open) {
            connectedU.union(center.id, neighbour.id);
            opened.union(center.id, neighbour.id);
        }
    }
    public boolean isOpen(int row, int col) {
        if (row >= grid.length || col >= grid.length) {
            throw new IndexOutOfBoundsException();
        }
        return (grid[row][col].open);
    } // is the site (row, col) open?
    public boolean isFull(int row, int col) {
        if (row >= grid.length || col >= grid.length) {
            throw new IndexOutOfBoundsException();
        }
        return (opened.connected(grid[row][col].id, N * N)
                && connectedU.connected(grid[row][col].id, N * N));
    } // is the site (row, col) full?
    public int numberOfOpenSites() {
        return counts;
    }          // number of open sites
    public boolean percolates() {
        return connectedU.connected(N * N + 1, N * N);
    } // does the system percolate?

    public static void main(String[] args) {

    }
}

