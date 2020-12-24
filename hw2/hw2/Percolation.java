package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF opened;
    private WeightedQuickUnionUF connectedU;
    private SquareObject[][] grid;
    private int N;
    private class SquareObject {
        private boolean open;
        private int id;
        SquareObject(int x, int y, int n) {
            open = false;
            id = x * n + y; //hashcode && Binary
        }
    }

    public Percolation(int N) {
        if (N < 1) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        grid = new SquareObject[N][N];
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                grid[i][j] = new SquareObject(i, j, N);
            }
        }
        opened = new WeightedQuickUnionUF(N * N + 1);
        connectedU = new WeightedQuickUnionUF(N * N + 1);
    }                // create N-by-N grid, with all sites initially blocked
    public void open(int row, int col) {
        if (row >= grid.length || col >= grid.length) {
            throw new IndexOutOfBoundsException();
        }
        grid[row][col].open = true;
        opened.union(grid[row][col].id, N * N);
        if (row == 0) {
            connectedU.union(N * N, grid[row][col].id);
            if ((row + 1) < N) {
                connection(grid[row][col], grid[row + 1][col]);
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
        return (connectedU.connected(grid[row][col].id, N * N));
    } // is the site (row, col) full?
    public int numberOfOpenSites() {
        return N * N + 1 - opened.count();
    }          // number of open sites
    public boolean percolates() {
        for (int i = 0; i < grid.length; i += 1) {
            if (connectedU.connected(grid[grid.length - 1][i].id, N * N)) {
                return true;
            }
        }
        return false;
    } // does the system percolate?

    public static void main(String[] args) {

    }
}

