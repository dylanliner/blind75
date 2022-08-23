package blind75;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AddAndSearchWord {

    class WordDictionary {

        Trie trie = new Trie();

        public WordDictionary() {

        }

        public void addWord2(String word) {
            var current = trie;
            for (Character c : word.toCharArray()) {
                current.map.computeIfAbsent(c, x -> new Trie());
                current = current.map.get(c);
            }
            current.end = true;
        }

        public boolean search2(String word) {
            return searchNext(word, 0, trie);
        }

        private boolean searchNext(String word, int index, Trie current) {
            if (index == word.length()) {
                return current.end;
            }

            var currentCharacter = word.charAt(index);
            if (currentCharacter == '.') {
                for (Character k : current.map.keySet()) {
                    if (searchNext(word, index + 1, current.map.get(k))) {
                        return true;
                    }
                    ;
                }
            } else {
                return current.map.get(currentCharacter) != null &&
                        searchNext(word, index + 1, current.map.get(currentCharacter));
            }

            return false;
        }


        public class Trie {
            HashMap<Character, Trie> map = new HashMap<>();

            boolean end;
        }

        public void addWord(String word) {

            var current = trie;
            for (Character c : word.toCharArray()) {
                current.map.computeIfAbsent(c, x -> new Trie());
                current = current.map.get(c);
            }
            current.end = true;


        }

        public boolean search(String word) {

            return searchRecursive(word, 0, trie);

        }

        public boolean searchRecursive(String word, int index, Trie trie) {

            if (word.length() == index) {
                return trie.end;
            }

            if (word.charAt(index) == '.') {
                for (Character c : trie.map.keySet()) {
                    if (searchRecursive(word, index + 1, trie.map.get(c))) {
                        return true;
                    }
                }
            } else if (trie.map.get(word.charAt(index)) == null) {
                return false;
            } else {
                return searchRecursive(word, index + 1, trie.map.get(word.charAt(index)));
            }
            return false;

        }

    }


    @Test
    public void test() {
        var wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad")); // return False
        System.out.println(wordDictionary.search("bad")); // return True
        System.out.println(wordDictionary.search(".ad")); // return True*/
        System.out.println(wordDictionary.search("b..")); // return True
        System.out.println("---");

        var wordDictionary2 = new WordDictionary();
        wordDictionary2.addWord("a");
        wordDictionary2.addWord("a");
        System.out.println(wordDictionary2.search("."));
        System.out.println(wordDictionary2.search("a")); // return True
        System.out.println(wordDictionary2.search("aa")); // return True
        System.out.println(wordDictionary2.search("a")); // return True
        System.out.println(wordDictionary2.search(".a")); // return True
        System.out.println(wordDictionary2.search("a.")); // return True
    }
}
