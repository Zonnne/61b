package byog.Core;

public class UnionFind {
    //item[] verticles;
    int[] parents, sizes;

    public UnionFind(int n) {
        //item[] verticles = (item []) new Object[n];
        parents = new int[n];
        sizes = new int[n];
        for (int i = 0; i < n; i += 1) {
            parents[i] = -i;
            sizes[i] = 1;
        }
    }
    public void validate(int v1) {
        if (v1 >= parents.length) {
            throw new IllegalArgumentException("???");
        }
    }
    public int find(int v1) {
        validate(v1);
        if (parents[v1] <= 0) {
            return parents[v1];
        } else {
            return find(parents[v1]);
        }
    }
    public void union(int v1, int v2) {
        int root1 = -find(v1);
        int root2 = -find(v2);
        if (root1 == root2) {
            return;
        }
        if (sizes[root1] <= sizes[root2]) {
            parents[v1] = v2;
            sizes[root2] += sizes[root1];
        } else {
            parents[v2] = v1;
            sizes[root1] += sizes[root2];
        }
    }
    public int sizeOf(int v1) {
        validate(v1);
        return sizes[v1];
    }
    public int parent(int v1) {
        validate(v1);
        if (parents[v1] == -v1) {
            return -sizes[v1];
        }
        return parents[v1];
    }
    public boolean connected(int v1, int v2) {
        return (find(v1) == find(v2));
    }
    public static void main(String[] args) {
        // write your code here
        UnionFind dj = new UnionFind(12);
        dj.union(1, 2);
        dj.union(2, 3);
        dj.union(4, 5);
        dj.union(5, 6);
        System.out.println("parent is " + dj.parent(4));
    }
}
