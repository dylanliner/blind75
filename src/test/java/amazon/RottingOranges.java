package amazon;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class RottingOranges {

    public int orangesRotting(int[][] grid) {

        Queue<int[]> queue = new ArrayDeque<>();

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 2) {
                    queue.add(new int[]{row, col, 0});
                }
            }
        }


        //BFS
        var minutes = 0;
        while (!queue.isEmpty()) {
            var curOrange = queue.poll();
            minutes = curOrange[2];
            var adjOrangeList = getAdjOranges(curOrange, grid);
            queue.addAll(adjOrangeList);
        }

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    return -1;
                }
            }
        }

        return minutes;

    }

    private List<int[]> getAdjOranges(int[] curOrange, int[][] grid) {
        var row = curOrange[0];
        var col = curOrange[1];
        var min = curOrange[2];
        var ans = new ArrayList<int[]>();
        addAdjOrange(grid, row + 1, col, min + 1, ans);
        addAdjOrange(grid, row - 1, col, min + 1, ans);
        addAdjOrange(grid, row, col + 1, min + 1, ans);
        addAdjOrange(grid, row, col - 1, min + 1, ans);
        return ans;

    }

    private static void addAdjOrange(int[][] grid, int row, int col, int min, ArrayList<int[]> ans) {
        if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == 1) {
            grid[row][col] = 2;
            ans.add(new int[]{row, col, min});
        }
    }

    @Test
    public void test() {
        System.out.println(orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}));
        System.out.println(orangesRotting(new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}}));
        System.out.println(orangesRotting(new int[][]{{0,1}}));
        System.out.println(orangesRotting(new int[][]{{2,2},{1,1},{0,0},{2,0}}));
    }
}
