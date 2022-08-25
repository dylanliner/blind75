package amazon;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CountUniqueCharactersOfAllSubstringOfGivenString {


    public int uniqueLetterString2(String s) {
        //Generate All Substrings
        var ans = 0;
        for (int i = 0; i < s.length(); i++) {
            var substring = new HashMap<Character, Integer>();
            var count = 0;
            for (int j = i; j < s.length(); j++) {
                if (!substring.containsKey(s.charAt(j))) {
                    count++;
                } else if (substring.get(s.charAt(j)) < 2) {
                    count--;
                }
                substring.merge(s.charAt(j), 1, Integer::sum);
                ans = ans + count;
            }
        }
        return ans;


    }

    public int uniqueLetterString(String s) {
        //count all possible substrings to have one letter in it
        var ans = 0;
        var map = new HashMap<Character, int[]>();
        for (int i = 0; i < s.length(); i++) {
            map.computeIfAbsent(s.charAt(i), c -> new int[]{-1, -1});
            ans = ans + (i - map.get(s.charAt(i))[1]) * (map.get(s.charAt(i))[1] - map.get(s.charAt(i))[0]);
            map.get(s.charAt(i))[0] = map.get(s.charAt(i))[1];
            map.get(s.charAt(i))[1] = i;
        }


        for (int[] lastTwoIndex : map.values()) {
            ans = ans + (s.length() - lastTwoIndex[1]) * (lastTwoIndex[1] - lastTwoIndex[0]);
        }

        return ans;
    }

    @Test
    public void test() {
        System.out.println(uniqueLetterString("ABA"));
        System.out.println(uniqueLetterString("ABC"));
        System.out.println(uniqueLetterString("LEETCODE"));
    }
}
