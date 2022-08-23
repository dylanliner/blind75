package blind75;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MergeIntervals {

    @Test
    public void test() {

        var intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        var intervals2 = new int[][]{{1, 4}, {4, 5}};
        var intervals3 = new int[][]{{1, 4}, {0, 4}};
        var intervals4 = new int[][]{{1, 4}, {2, 3}};
        System.out.println(Arrays.deepToString(merge(intervals)));
        System.out.println(Arrays.deepToString(merge(intervals2)));
        System.out.println(Arrays.deepToString(merge(intervals3)));
        System.out.println(Arrays.deepToString(merge(intervals4)));
    }

    public int[][] merge2(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        var array = new ArrayList<int[]>();
        var end = intervals[0][1];
        var start = intervals[0][0];

        for (int i = 1; i < intervals.length; i++) {
            if (end < intervals[i][0]) {
                array.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            } else {
                end = Math.max(end, intervals[i][1]);
            }
        }
        array.add(new int[]{start, end});

        return array.toArray(new int[array.size()][]);

    }

    public int[][] merge(int[][] intervals) {
        //sort by start of intervals
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        ArrayList<int[]> ans = new ArrayList<int[]>();
        var start = intervals[0][0];
        var end = intervals[0][1];
        //start loop
        for (int i = 1; i < intervals.length; i++) {

            //if overlapping with previous take the largest end of the two
            if (intervals[i][0] <= end) {
                end = Math.max(end, intervals[i][1]);
            } else {
                //if not overlapping reset start and end and add to answer
                ans.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        ans.add(new int[]{start, end});

        return ans.toArray(new int[0][]);
    }
}
