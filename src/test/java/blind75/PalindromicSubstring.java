package blind75;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PalindromicSubstring {
    int ans = 0;

    public int countSubstrings(String s) {

        for (int i = 0; i < s.length(); i++) {
            checkIfPalindrome(s, i);
        }
        return ans;

    }

    private void checkIfPalindrome(String s, int i) {
        var j = i;
        var k = i;

        while (0 <= j && k < s.length() && s.charAt(j) == s.charAt(k)) {

            ans++;

            j--;
            k++;
        }

        j = i;
        k = i + 1;
        while (0 <= j && k < s.length() && s.charAt(j) == s.charAt(k)) {
            ans++;
            j--;
            k++;
        }

    }

    @Test
    public void test() {
        var s1 = "abc";
        var s2 = "aaa";
        var s3 = "raddar";
        var s4 = "radartradar";
        var s5 = "leetcode";
        System.out.println(countSubstrings(s5));
        ans = 0;
        System.out.println(countSubstrings(s1));
        ans = 0;
        System.out.println(countSubstrings(s2));
        ans = 0;
        System.out.println(countSubstrings(s3));
        ans = 0;
        System.out.println(countSubstrings(s4));
        ans = 0;

    }

    @BeforeEach
    public void clean() {

    }
}
