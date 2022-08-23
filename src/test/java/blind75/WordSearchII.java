package blind75;

import org.junit.jupiter.api.Test;

import java.util.*;

public class WordSearchII {


    @Test
    public void test() {
        var board = new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        var words = new String[]{"oath", "pea", "eat", "rain"};
        var board2 = new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        var words2 = new String[]{"o", "oa", "oat", "oath"};
        System.out.println(findWords(board, words));
        System.out.println(findWords(board2, words2));
    }

    public List<String> findWords(char[][] board, String[] words) {
        //insert all words in trie
        var trie = new Trie();
        for (String word : words) {
            insertInTrie(word, trie);
        }
        //check if all words in trie are in board
        var arr = new ArrayList<String>();
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                dfs(board, trie, row, column, arr);
            }
        }
        return arr;

    }

    private void insertInTrie(String word, Trie trie) {
        for (char c : word.toCharArray()) {
            trie.map.computeIfAbsent(c, x -> new Trie());
            trie = trie.map.get(c);
        }
        trie.word = word;
    }

    private void dfs(char[][] board, Trie trie, int row, int column, ArrayList<String> arr) {

        if (trie.word != null) {

            arr.add(trie.word);
            trie.word = null;
        }

        if (0 <= row && row < board.length && 0 <= column && column < board[0].length) {

            if (trie.map.get(board[row][column]) != null && board[row][column] != '$') {

                trie = trie.map.get(board[row][column]);
                var c = board[row][column];
                board[row][column] = '$';
                dfs(board, trie, row - 1, column, arr);
                dfs(board, trie, row + 1, column, arr);
                dfs(board, trie, row, column + 1, arr);
                dfs(board, trie, row, column - 1, arr);
                board[row][column] = c;
            }

        }

    }

    class Trie {
        HashMap<Character, Trie> map = new HashMap<>();
        String word;
    }
}
