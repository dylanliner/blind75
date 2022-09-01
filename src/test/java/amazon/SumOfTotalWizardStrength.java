package amazon;

import org.junit.jupiter.api.Test;

import java.util.Stack;

public class SumOfTotalWizardStrength {

    public int totalStrength2(int[] strength) {
        long mod = 1000000007;
        long total = 0;
        for (int i = 0; i < strength.length; i++) {
            long curSubArrayMin = strength[i];
            long curTotalSubArray = 0;
            for (int j = i; j < strength.length; j++) {
                curSubArrayMin = Math.min(curSubArrayMin, strength[j]);
                curTotalSubArray = curTotalSubArray + strength[j];
                total = total + (curSubArrayMin * curTotalSubArray);
            }
        }

        return (int) (total % mod);

    }

    public int totalStrength(int[] strength) {

        //find all subarrays where i is the weakest using monotone stack,
        var mod = 1_000_000_007;
        var prevLess = new int[strength.length];
        var nextLess = new int[strength.length];
        var stack = new Stack<Integer>();
        for (int i = 0; i < strength.length; i++) {

            while (!stack.isEmpty() && strength[stack.peek()] > strength[i]) {
                stack.pop();
            }
            prevLess[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.add(i);

        }
        stack = new Stack<Integer>();
        for (int i = strength.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && strength[stack.peek()] >= strength[i]) {
                stack.pop();
            }
            nextLess[i] = stack.isEmpty() ? strength.length : stack.peek();
            stack.add(i);

        }

        //prefix sum
        //find sum of these sub arrays
        var prefixSum = new long[strength.length + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= strength.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + strength[i - 1] % mod;
        }
        var sumOfPrefixSum = new long[strength.length + 2];
        sumOfPrefixSum[0] = 0;
        for (int i = 1; i <= strength.length + 1; i++) {
            sumOfPrefixSum[i] = sumOfPrefixSum[i - 1] + prefixSum[i - 1] % mod;
        }


        long ans = 0;
        //multiply i * sum
        for (int i = 0; i < strength.length; i++) {
            ans += strength[i] * (
                    ((sumOfPrefixSum[nextLess[i] + 1] - sumOfPrefixSum[i + 1]) * (i - prevLess[i])) % mod + mod -
                            ((sumOfPrefixSum[i + 1] - sumOfPrefixSum[prevLess[i] + 1]) * (nextLess[i] - i))  % mod
            );
            ans %= mod;
        }


        return (int) ans;

    }

    @Test
    public void test() {

        System.out.println(totalStrength(new int[]{1, 3, 1, 2}));
        System.out.println(totalStrength(new int[]{5, 4, 6}));
    }
}
