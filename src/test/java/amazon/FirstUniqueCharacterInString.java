package amazon;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FirstUniqueCharacterInString {

    public int firstUniqChar(String s) {
        //store in map index and character,
        //remove duplicate character
        var map = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            map.merge(s.charAt(i), 1, Integer::sum);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;

    }

    @Test
    public void test() {
        System.out.println(firstUniqChar("leetcode"));
    }
}
