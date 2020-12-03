public class OffByN implements CharacterComparator {
    private int N;
    public OffByN(int x) {
        N = x;
    }
    public boolean equalChars(char x, char y) {
        return ((x - y) * (x - y) == N*N );
    }
}
