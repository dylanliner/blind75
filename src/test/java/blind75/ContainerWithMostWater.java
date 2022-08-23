package blind75;

import org.junit.jupiter.api.Test;

public class ContainerWithMostWater {

    public int maxArea2(int[] height) {

        var maxArea = 0;

        for (int i = 0; i < height.length; i++) {
            for (int j = 0; j < height.length; j++) {
                maxArea = Math.max(Math.min(height[i], height[j]) * Math.abs(i - j), maxArea);
            }
        }
        return maxArea;
    }

    public int maxArea(int[] height) {

        var maxArea = 0;

        var j = 0;
        var k = height.length - 1;
        while (j < k) {
            maxArea = Math.max(maxArea, Math.min(height[j], height[k]) * Math.abs(k - j));
            if (height[j] < height[k]) {
                j++;
            } else {
                k--;
            }
        }

        return maxArea;

    }

    @Test
    public void test() {

        var input = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(input));
    }
}
