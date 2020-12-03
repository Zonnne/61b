public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> strings = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            strings.addLast(word.charAt(i));
        }
        return strings;
    }
    private boolean BiRemove(Deque item) {
        if (item.size() <= 1) {
            return true;
        }
        return (item.removeFirst() == item.removeLast() && BiRemove(item));
    }
    public boolean isPalindrome(String word) {
        Deque<Character> strings = wordToDeque(word);
        return BiRemove(strings);
    }
}
