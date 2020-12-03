public class OffByOne implements CharacterComparator {
    public boolean equalChars(char x, char y) {
        return ((x - y) * (x - y) == 1 );
    }
}
