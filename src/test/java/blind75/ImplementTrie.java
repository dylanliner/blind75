package blind75;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class ImplementTrie {

    class Trie {

        HashMap<Character, Trie> map = new HashMap<>();

        boolean end;

        public Trie() {

        }

        public void insert(String word) {
            var current = this;
            for (Character c : word.toCharArray()) {
                current.map.computeIfAbsent(c, x -> new Trie());
                current = current.map.get(c);
            }
            current.end = true;
        }

        public boolean search(String word) {
            var current = this;
            for (Character c : word.toCharArray()) {
                if (current.map.get(c) == null) {
                    return false;
                } else {
                    current = current.map.get(c);
                }
            }
            return current.end;

        }

        public boolean startsWith(String prefix) {
            var current = this;
            for (Character c : prefix.toCharArray()) {
                if (current.map.get(c) == null) {
                    return false;
                } else {
                    current = current.map.get(c);
                }
            }
            return true;
        }
    }

    @Test
    public void test() {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // return True
        System.out.println(trie.search("app"));     // return False
        System.out.println(trie.startsWith("app"));       // return True
        trie.insert("app");
        System.out.println(trie.search("app"));     // return True
        System.out.println(trie.startsWith("apl"));
        System.out.println(trie.startsWith("app"));
    }
}
