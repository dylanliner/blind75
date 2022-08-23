package blind75;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class InsertIntervals {

    @Test
    public void test() {
        var intervals = new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        var newInterval = new int[]{4, 8};
        System.out.println(Arrays.deepToString(insert(intervals, newInterval)));
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        var arr = new ArrayList<>(Arrays.asList(intervals));
        arr.add(newInterval);
        arr.sort(Comparator.comparing(a -> a[0]));
        var start = arr.get(0)[0];
        var end = arr.get(0)[1];
        var ans = new ArrayList<int[]>();
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i)[0] <= end) {
                end = Math.max(end, arr.get(i)[1]);
            } else {
                ans.add(new int[]{start, end});
                start = arr.get(i)[0];
                end = arr.get(i)[1];
            }
        }
        ans.add(new int[]{start, end});
        return ans.toArray(new int[0][]);
    }
}
