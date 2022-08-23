package amazon;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TrappingRainWater {

    @Test
    public void test() {
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(trap2(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    public int trap(int[] height) {
        var max = Arrays.stream(height).max().getAsInt();
        var currentHeight = 0;
        var trapped = 0;
        while (currentHeight <= max) {
            var p1 = 0;
            var p2 = 0;
            var isEnclosed = false;
            while (p2 < height.length) {

                if (height[p1] > currentHeight && !isEnclosed) {
                    p2++;
                    isEnclosed = true;
                    continue;
                }

                if (height[p2] <= currentHeight && isEnclosed) {
                    p2++;
                } else if (isEnclosed) {
                    isEnclosed = false;
                    trapped = trapped + (p2 - p1) - 1;
                    p1 = p2;
                } else {
                    p1++;
                    p2++;
                }

            }
            currentHeight++;
        }
        return trapped;

    }

    public int trap2(int[] height) {
        var left = 0;
        var right = height.length - 1;
        var maxLeft = 0;
        var maxRight = 0;
        var trapped = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= maxLeft) {
                    maxLeft = height[left];
                } else {
                    trapped = trapped + (maxLeft - height[left]);
                }
                left++;


            } else {
                if (height[right] >= maxRight) {
                    maxRight = height[right];
                } else {
                    trapped = trapped + (maxRight - height[right]);
                }
                right--;
            }
        }
        return trapped;
    }
}
