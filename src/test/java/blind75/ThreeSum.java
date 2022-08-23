package blind75;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ThreeSum {


    public List<List<Integer>> threeSum(int[] nums) {

        var set = new HashSet<List<Integer>>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {

            var j = i + 1;
            var k = nums.length - 1;

            while (j < k) {
                var sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    set.add(List.of(nums[i], nums[j++], nums[k--]));
                }
                if (sum < 0) {
                    j++;
                }
                if (sum > 0) {
                    k--;
                }


            }
        }

        return new ArrayList<>(set);

    }


    @Test
    public void test() {
        var nums = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));

    }

}
