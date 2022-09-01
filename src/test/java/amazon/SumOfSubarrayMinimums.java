package amazon;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Stack;

public class SumOfSubarrayMinimums {

    public int sumSubarrayMins(int[] arr) {

        var stack = new Stack<Integer>();
        var pleArr = new long[arr.length];
        var nleArr = new long[arr.length];

        //for every i, find ple
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            pleArr[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.add(i);
        }

        stack = new Stack<Integer>();
        //for every i, find nle
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            nleArr[i] = stack.isEmpty() ? arr.length - i : stack.peek() - i;
            stack.add(i);
        }

        var sum = 0l;
        var mod = 1000000007l;
        for (int i = 0; i < arr.length; i++) {
            sum = (sum + pleArr[i] * nleArr[i] * arr[i])%mod;

        }

        return (int) sum;

    }

    @Test
    public void test() {
        System.out.println(sumSubarrayMins(new int[]{3, 1, 2, 4}));
        System.out.println(sumSubarrayMins(new int[]{11, 81, 94, 43, 3}));
        System.out.println(sumSubarrayMins(new int[]{71, 55, 82, 55}));
    }
}
