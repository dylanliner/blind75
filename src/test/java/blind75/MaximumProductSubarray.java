package blind75;

import org.junit.jupiter.api.Test;

public class MaximumProductSubarray {

    @Test
    public void test() {

        var inputs = new int[]{2, 3, -2, 4};
        var inputs2 = new int[]{0, 2};
        var inputs3 = new int[]{-2, 3, -4};
        var inputs4 = new int[]{-1, -2, -9, -6};
        var inputs5 = new int[]{-3,0,1,-2};
        //System.out.println(maxProduct(inputs));
        //System.out.println(maxProduct(inputs2));
        //System.out.println(maxProduct(inputs3));
        //System.out.println(maxProduct(inputs4));
        System.out.println(maxProduct(inputs5));
    }

    public int maxProduct(int[] nums) {
        var max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            var product = nums[i];
            int j = i;
            max = Math.max(product, max);
            while (j < nums.length - 1) {
                product = product * nums[j + 1];
                max = Math.max(product, max);
                j++;
            }

        }
        return max;
    }
}
