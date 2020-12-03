public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> strings = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            strings.addLast(word.charAt(i));
        }
        return strings;
    }
    private boolean biRemove(Deque<Character> item) {
        if (item.size() <= 1) {
            return true;
        }
        return (item.removeFirst() == item.removeLast() && biRemove(item));
    }
    private boolean biRemove(Deque<Character> item, CharacterComparator cc) {
        if (item.size() <= 1) {
            return true;
        }
        return (cc.equalChars(item.removeFirst(), item.removeLast()) && biRemove(item, cc));
    }
    public boolean isPalindrome(String word) {
        Deque<Character> strings = wordToDeque(word);
        return biRemove(strings);
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> strings = wordToDeque(word);
        return biRemove(strings, cc);
    }
}
