package amazon;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SumOfSubArrayRanges {

    public long subArrayRanges(int[] nums) {

        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            var min = nums[i];
            var max = nums[i];
            for (int j = i; j < nums.length; j++) {
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
                sum = sum + (max - min);
            }
        }
        return sum;

    }

    @Test
    public void test() {
        System.out.println(subArrayRanges(new int[]{1, 3, 3}));
        System.out.println(subArrayRanges(new int[]{4,-2,-3,4,1}));
    }
}
