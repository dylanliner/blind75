package blind75;

import org.junit.jupiter.api.Test;

import java.util.*;

public class WordSearch {

    @Test
    public void test() {
        var board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        var word = "ABCCED";
        System.out.println(exist(board, word));
        var board2 = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        var word2 = "ABCB";
        System.out.println(exist(board2, word2));
        var board3 = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}};
        var word3 = "ABCESEEEFS";
        System.out.println(exist(board3, word3));
    }

    public boolean exist(char[][] board, String word) {
        var visited = new boolean[board.length][board[0].length];
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (board[row][column] == word.charAt(0)) {
                    if (recursiveDfs(board, word, row, column, 0, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean recursiveDfs(char[][] board, String word, int row, int column, int index, boolean[][] visited) {


        if (index == word.length()) {
            return true;
        }

        if (row >= 0 && row < board.length && column >= 0 && column < board[0].length) {
            if (visited[row][column]) {
                return false;
            }
            visited[row][column] = true;
            if (board[row][column] == word.charAt(index)
                    && (recursiveDfs(board, word, row, column + 1, index + 1, visited)
                    || recursiveDfs(board, word, row, column - 1, index + 1, visited)
                    || recursiveDfs(board, word, row + 1, column, index + 1, visited)
                    || recursiveDfs(board, word, row - 1, column, index + 1, visited)
            )) {
                return true;
            }
            visited[row][column] = false;
        }
        return false;
    }

    public boolean exist2(char[][] board, String word) {

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                if (word.charAt(0) == board[row][column]) {
                    if (dfs(board, word, row, column)) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    public boolean dfs(char[][] board, String word, int row, int column) {


        var neighbors = neighbors(board, row, column);
        var visited = new HashSet<List<Integer>>();
        var toVisit = new Stack<List<Integer>>();
        toVisit.add(List.of(row, column, 0));

        while (!toVisit.isEmpty()) {

            var current = toVisit.pop();
            if (current.get(2) + 1 == word.length()) {
                return true;
            }
            visited.add(List.of(current.get(0), current.get(1)));
            var searchedLetter = word.charAt(current.get(2) + 1);
            neighbors = neighbors(board, current.get(0), current.get(1));
            for (List<Integer> neighbor : neighbors) {
                if (!visited.contains(neighbor) && board[neighbor.get(0)][neighbor.get(1)] == searchedLetter) {

                    toVisit.add(List.of(neighbor.get(0), neighbor.get(1), current.get(2) + 1));
                }
            }
        }
        return false;
    }

    public List<List<Integer>> neighbors(char[][] board, int row, int column) {
        var ans = new ArrayList<List<Integer>>();
        extracted(board, row + 1, column, ans);
        extracted(board, row - 1, column, ans);
        extracted(board, row, column + 1, ans);
        extracted(board, row, column - 1, ans);
        return ans;
    }

    private void extracted(char[][] board, int row, int column, ArrayList<List<Integer>> ans) {
        if (row < board.length && row >= 0 && column < board[0].length && column >= 0) {
            ans.add(List.of(row, column));
        }
    }
}
