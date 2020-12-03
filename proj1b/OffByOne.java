public class OffByOne implements CharacterComparator {
    public boolean equalChars(char x, char y) {
        if (!((((x < 91) && (x > 64)) && ((x < 123) && (x > 96)))
            && (((y < 91) && (y > 64)) && ((y < 123) && (y > 96))))) {
            return false;
        }
        return ((x - y) * (x - y) == 1);
    }
}
