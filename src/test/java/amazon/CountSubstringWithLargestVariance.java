package amazon;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

public class CountSubstringWithLargestVariance {

    public int largestVariance2(String s) {

        var max = 0;
        var hasDifferentLetters = false;
        for (int i = 0; i < s.length(); i++) {
            var charCount = new HashMap<Character, Integer>();
            for (int j = i; j < s.length(); j++) {
                charCount.merge(s.charAt(j), 1, Integer::sum);
                var charCountArr = new ArrayList<>(charCount.values());
                Collections.sort(charCountArr);
                max = Math.max(max, charCountArr.get(charCountArr.size() - 1) - charCountArr.get(0));
            }
            if (i < s.length() - 1 && s.charAt(i) != s.charAt(i + 1)) {
                hasDifferentLetters = true;
            }
        }

        return hasDifferentLetters ? max : 0;

    }

    public int largestVariance(String s) {

        var max = 0;
        var charCount = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            charCount.merge(s.charAt(i), 1, Integer::sum);
        }
        var characters = new ArrayList<>(charCount.keySet());


        for (int i = 0; i < characters.size(); i++) {


            for (int j = 0; j < characters.size(); j++) {
                var currentCountA = 0;
                var currentCountB = 0;
                var currentA = characters.get(i);
                var currentB = characters.get(j);
                var remainingA = charCount.get(characters.get(i));

                if (currentB == currentA) {
                    continue;
                }

                for (int k = 0; k < s.length(); k++) {
                    var current = s.charAt(k);

                    if (currentA == current) {
                        currentCountA++;
                        remainingA--;
                    }
                    if (currentB == current) {
                        currentCountB++;
                    }

                    if (currentCountA > 0) {
                        max = Math.max(max, currentCountB - currentCountA);
                    }

                    if (currentCountA > currentCountB && remainingA > 0) {
                        currentCountA = 0;
                        currentCountB = 0;
                    }


                }

            }
        }

        return max;

    }

    @Test
    public void test() {
        System.out.println(largestVariance("aaaabbbbbabb"));
        System.out.println(largestVariance("aababbb"));
        System.out.println(largestVariance("aaaaa"));
        System.out.println(largestVariance("ababab"));

    }
}
