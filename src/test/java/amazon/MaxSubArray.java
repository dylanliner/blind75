package amazon;

public class MaxSubArray {

    public int maxSubArray(int[] nums) {

        var ans = nums[0];
        var currentMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            ans = Math.max(ans, currentMax);
        }
        return ans;

    }
}
