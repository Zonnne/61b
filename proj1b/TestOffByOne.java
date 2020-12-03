import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();
    @Test
    public void testEqualChars() {
        offByOne.equalChars('a', 'b');
        assertTrue(offByOne.equalChars('A', 'B'));
        assertFalse(offByOne.equalChars('v', 'v'));
        assertFalse(offByOne.equalChars('A', 'A'));
        assertTrue(offByOne.equalChars('A', 'B'));
        assertTrue(offByOne.equalChars('B', 'A'));
        assertFalse(offByOne.equalChars('A', 'C'));
        assertFalse(offByOne.equalChars('C', 'A'));
        assertTrue(offByOne.equalChars('%', '&'));
        assertTrue(offByOne.equalChars('&', '%'));
        assertFalse(offByOne.equalChars('$', '&'));
        assertFalse(offByOne.equalChars('&', '$'));
    }
    // Your tests go here.
    //Uncomment this class once you've created your Character
    // Comparator interface and OffByOne class. **/
}
