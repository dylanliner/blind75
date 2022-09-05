package amazon;

import org.junit.jupiter.api.Test;

import java.util.*;

public class CourseSchedule {

    public boolean canFinish2(int numCourses, int[][] prerequisites) {

        //need to find loop
        var map = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < prerequisites.length; i++) {
            map.computeIfAbsent(prerequisites[i][0], s -> new ArrayList<>()).add(prerequisites[i][1]);
        }

        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            if (!recursion(map, entry.getKey(), new HashSet<>())) {
                return false;
            }
        }
        return true;

    }

    private boolean recursion(HashMap<Integer, List<Integer>> map, int cur, Set<Integer> visited) {
        if (visited.contains(cur)) {
            return false;
        }
        visited.add(cur);
        if (!map.containsKey(cur)) {
            return true;
        }
        for (Integer pre : map.get(cur)) {
            if (!recursion(map, pre, new HashSet<>(visited))) {
                return false;
            }

        }
        return true;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        //edge count
        var edgeCountsByCourse = new int[numCourses];
        var destCoursesByBeginCourse = new HashMap<Integer, List<Integer>>();
        //list of startCourseWithDestCourses
        for (int i = 0; i < prerequisites.length; i++) {
            edgeCountsByCourse[prerequisites[i][0]]++;
            destCoursesByBeginCourse.computeIfAbsent(prerequisites[i][1], s -> new ArrayList<>()).add(prerequisites[i][0]);
        }


        var queue = new ArrayDeque<Integer>();


        for (int i = 0; i < numCourses; i++) {
            if (edgeCountsByCourse[i] == 0) {
                queue.add(i);
            }

        }
        var count = 0;
        while (!queue.isEmpty()) {
            var cur = queue.poll();
            count++;
            if (!destCoursesByBeginCourse.containsKey(cur)) {
                continue;
            }

            for (int dest : destCoursesByBeginCourse.get(cur)) {
                edgeCountsByCourse[dest]--;
                if (edgeCountsByCourse[dest] == 0) {
                    queue.add(dest);
                }

            }
        }

        return numCourses == count;


    }

    @Test
    public void test() {
        //topological sort
        System.out.println(canFinish(2, new int[][]{{1, 0}}));
        System.out.println(canFinish(2, new int[][]{{1, 0}, {0, 1}}));
        System.out.println(canFinish(6, new int[][]{{5, 5}}));
        System.out.println(canFinish(4, new int[][]{{2, 0}, {1, 0}, {3, 1}, {3, 2}, {1, 3}}));
    }
}
