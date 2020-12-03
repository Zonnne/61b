import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    @Test
    public void testIsPalindrome() {
        boolean x = palindrome.isPalindrome("nooooon");
        assertTrue(x);
        boolean y = palindrome.isPalindrome("asddsa");
        assertTrue(y);
        boolean z = palindrome.isPalindrome("apple");
        assertFalse(z);
    }
    @Test
    public void testIsPalindromeOffByOne() {
        OffByOne cc = new OffByOne();
        boolean x = palindrome.isPalindrome("abacb", cc);
        assertTrue(x);
        boolean y = palindrome.isPalindrome("asddsa", cc);
        assertFalse(y);
        boolean z = palindrome.isPalindrome("opedqp", cc);
        assertTrue(z);
    }
    @Test
    public void testIsPalindromeOffByN() {
        OffByN cc = new OffByN(1);
        boolean x = palindrome.isPalindrome("abacb", cc);
        assertTrue(x);
        boolean y = palindrome.isPalindrome("asddsa", cc);
        assertFalse(y);
        boolean z = palindrome.isPalindrome("opedqp", cc);
        assertTrue(z);
        OffByN cc5 = new OffByN(5);
        boolean x5 = palindrome.isPalindrome("abagf", cc5);
        assertTrue(x5);
        boolean y5 = palindrome.isPalindrome("asddsa", cc5);
        assertFalse(y5);
        boolean z5 = palindrome.isPalindrome("glrwql", cc5);
        assertTrue(z5);
    }
    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    } //Uncomment this class once you've created your Palindrome class. */
}
