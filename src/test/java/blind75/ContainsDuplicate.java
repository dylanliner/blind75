package blind75;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        var set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test() {
        var inputs = new int[]{1, 2, 3};
        System.out.println(containsDuplicate(inputs));
    }
}
