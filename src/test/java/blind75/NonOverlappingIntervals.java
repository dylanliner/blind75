package blind75;

import main.java.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals {

    @Test
    public void test() {
        var input = new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println(eraseOverlapIntervals(input));

        var input2 = new int[][]{{1, 100}, {11, 22}, {1, 11}, {2, 12}};
        System.out.println(eraseOverlapIntervals(input2));
    }


    public int eraseOverlapIntervals2(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        if (intervals.length < 2) {
            return 0;
        }
        var offset = 0;
        var prevHighBound = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {

            var currentLowBound = intervals[i][0];
            if (prevHighBound > currentLowBound) {
                offset++;
                prevHighBound = Math.min(prevHighBound, intervals[i][1]);
            } else {
                prevHighBound = intervals[i][1];
            }


        }
        return offset;
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        if (intervals.length <= 1) {
            return 0;
        }
        int prevEnd = intervals[0][1];
        var count = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= prevEnd) {
                count++;
                prevEnd = intervals[i][1];
            }
        }
        return intervals.length - count;
    }

    ;
}
