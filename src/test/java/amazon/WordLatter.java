package amazon;

import org.junit.jupiter.api.Test;

import java.util.*;

public class WordLatter {

    int min = Integer.MAX_VALUE;
    boolean found = false;

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {

        var map = new HashMap<String, List<String>>();
        //build directed graph
        for (int i = 0; i < wordList.size(); i++) {
            var arr = findAdjacent(wordList.get(i), wordList);
            map.put(wordList.get(i), arr);
        }
        for (String word : wordList) {

            if (word.equals(beginWord)) {
                recur(word, endWord, new HashMap<>(map), 0);
            }
            if (checkIfDifferentByOne(word, beginWord)) {
                recur(word, endWord, new HashMap<>(map), 1);
            }

        }
        return found ? min : 0;
    }

    private void recur(String word, String endWord, HashMap<String, List<String>> map, int dist) {
        dist++;
        if (word.equals(endWord)) {
            min = Math.min(dist, min);
            found = true;

        }
        if (!map.containsKey(word)) {
            return;
        }
        var adjacents = map.remove(word);
        for (String adj : adjacents) {
            recur(adj, endWord, new HashMap<>(map), dist);
        }

    }

    private List<String> findAdjacent(String s, List<String> wordList) {
        var arr = new ArrayList<String>();
        for (String word : wordList) {
            if (!s.equals(word) && checkIfDifferentByOne(word, s)) {
                arr.add(word);
            }
        }
        return arr;
    }

    private boolean checkIfDifferentByOne(String val, String beginWord) {
        if (val.length() != beginWord.length()) {
            return false;
        }
        var diff = 0;
        for (int i = 0; i < val.length(); i++) {
            if (val.charAt(i) != beginWord.charAt(i)) {
                diff++;
            }
            if (diff > 1) {
                return false;
            }

        }
        return true;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        //build map of by generic transformation key
        var map = new HashMap<String, List<String>>();
        wordList.forEach(word -> {
                    var charArr = word.toCharArray();
                    for (char i = 0; i < charArr.length; i++) {
                        var genericWord = charArr.clone();
                        genericWord[i] = '*';
                        map.computeIfAbsent(String.valueOf(genericWord), s -> new ArrayList<>()).add(word);
                    }

                }

        );

        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(0, beginWord));
        var visited = new HashSet<String>(List.of(beginWord));

        //BFS
        while (!queue.isEmpty()) {
            var currentWord = queue.poll();
            if (currentWord.word.equals(endWord)) {
                return currentWord.dist + 1;
            }
            var charArr = currentWord.word.toCharArray();
            for (int i = 0; i < charArr.length; i++) {
                var genericWord = charArr.clone();
                genericWord[i] = '*';
                if (map.containsKey(String.valueOf(genericWord))) {
                    map.get(String.valueOf(genericWord)).forEach(adj ->
                            {
                                if (!visited.contains(adj)) {
                                    queue.add(new Pair(currentWord.dist + 1, adj));
                                    visited.add(adj);
                                }

                            }
                    );
                }

            }


        }


        return 0;
    }

    class Pair {
        int dist;
        String word;

        public Pair(int dist, String word) {
            this.dist = dist;
            this.word = word;
        }
    }

    @Test
    public void test() {
        System.out.println(ladderLength("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog")));
        System.out.println(ladderLength("hot", "dog", List.of("hot", "dog", "dot")));
        System.out.println(ladderLength("kiss", "tusk", List.of("miss", "dusk", "kiss", "musk", "tusk", "diss", "disk", "sang", "ties", "muss")));
    }

}
