package amazon;

import org.junit.jupiter.api.Test;

import java.util.*;

public class KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int k) {

        var treeMap = new TreeMap<Double, List<int[]>>();

        for (int x = 0; x < points.length; x++) {
            var dist = Math.sqrt(Math.pow(points[x][0], 2) + Math.pow(points[x][1], 2));
            treeMap.computeIfAbsent(dist, s -> new ArrayList<>()).add(new int[]{points[x][0], points[x][1]});
        }


        var ans = new int[k][];
        var mapValues = treeMap.values();
        var i = 0;
        for (List<int[]> dist : mapValues) {
            for (int[] point : dist) {
                if (i == k) {
                    return ans;
                }
                ans[i] = point;
                i++;
            }

        }
        return ans;
    }

    @Test
    public void test() {
        System.out.println(Arrays.deepToString(kClosest(new int[][]{{1, 3}, {-2, 2}}, 1)));
        System.out.println(Arrays.deepToString(kClosest(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2)));
    }

}
