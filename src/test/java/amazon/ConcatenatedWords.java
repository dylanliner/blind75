package amazon;

import org.junit.jupiter.api.Test;

import java.util.*;

public class ConcatenatedWords {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {

        //Put all words in wordSet n
        //for each word, look for every possible  substring in the wordSet. n * m * m
        //if the wordSet has 2 or more matches, add concatenatedword to resp array.


        //Put all words in wordSet n
        //for each word, look for every possible combination and see if it matches a word in wordset n
        //if the wordSet has 2 or more matches, add concatenatedword to resp array.

        var set = new HashSet<>(Arrays.asList(words));
        var ans = new HashSet<String>();
        for (String word : words) {
            findSubString(set, word, 0, ans, 0);
        }


        return new ArrayList<>(ans);
    }

    private void findSubString(HashSet<String> set, String word, int startIndex, Set<String> ans, int count) {
        if (startIndex == word.length() && count > 1) {
            ans.add(word);
        }
        StringBuilder sb = new StringBuilder();
        for (int j = startIndex; j < word.length(); j++) {
            sb.append(word.charAt(j));
            if (set.contains(sb.toString())) {
                findSubString(set, word, j + 1, ans, count + 1);

            }
        }
    }

    @Test
    public void test() {
        System.out.println(findAllConcatenatedWordsInADict(new String[]{"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"}));
        System.out.println(findAllConcatenatedWordsInADict(new String[]{"cat", "dog", "catdog"}));
    }
}
