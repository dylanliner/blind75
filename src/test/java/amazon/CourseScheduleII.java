package amazon;

import org.junit.jupiter.api.Test;

import java.util.*;

public class CourseScheduleII {


    public int[] findOrder2(int numCourses, int[][] prerequisites) {

        var map = new HashMap<Integer, HashSet<Integer>>();
        for (int[] prerequisite : prerequisites) {
            map.computeIfAbsent(prerequisite[0], s -> new HashSet<>()).add(prerequisite[1]);
        }
        var listNoPreReq = new ArrayList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (!map.containsKey(i)) {
                listNoPreReq.add(i);
            }
            ;
        }

        var list = new LinkedHashSet<>(listNoPreReq);
        for (Map.Entry<Integer, HashSet<Integer>> entry : map.entrySet()) {
            if (!recursive(entry.getValue(), map, list, new HashSet<>(List.of(entry.getKey())))) {
                return new int[]{};
            }
            ;
            list.add(entry.getKey());
            if (list.size() == numCourses) {
                return list.stream().mapToInt(i -> i).toArray();
            }
        }

        return listNoPreReq.stream().mapToInt(i -> i).toArray();
    }

    private boolean recursive(HashSet<Integer> prerequisites, HashMap<Integer, HashSet<Integer>> map, LinkedHashSet<Integer> list, HashSet<Integer> visited) {
        if (prerequisites != null) {
            for (Integer prerequisite : prerequisites) {
                if (visited.contains(prerequisite)) {
                    return false;
                } else if (!list.contains(prerequisite)) {
                    var newVisited = new HashSet<>(visited);
                    newVisited.add(prerequisite);
                    if (recursive(map.get(prerequisite), map, list, newVisited)) {
                        list.add(prerequisite);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //Build directed graph
        var adjacent = new ArrayList<List<Integer>>();
        var countOfEdge = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adjacent.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            adjacent.get(prerequisite[1]).add(prerequisite[0]);
            countOfEdge[prerequisite[0]]++;
        }


        List<Integer> queue = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            if (countOfEdge[i] == 0) {
                queue.add(i);
            }
        }

        var visited = new int[numCourses];
        var visitedIndex = 0;

        //BFS
        while (!queue.isEmpty()) {
            var cur = queue.remove(0);
            visited[visitedIndex++] = cur;
            for (Integer neigh : adjacent.get(cur)) {
                countOfEdge[neigh]--;
                if (countOfEdge[neigh] == 0) {
                    queue.add(neigh);
                }

            }
        }

        return visitedIndex == numCourses ? visited : new int[]{0};


    }


    @Test
    public void test() {
        System.out.println(Arrays.toString(findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}})));
        //System.out.println(Arrays.toString(findOrder(2, new int[][]{{1, 0}})));
        //System.out.println(Arrays.toString(findOrder(2, new int[][]{})));
        //System.out.println(Arrays.toString(findOrder(2, new int[][]{{1, 0}, {0, 1}})));
        System.out.println(Arrays.toString(findOrder(3, new int[][]{{1, 0}, {2, 0}})));
        System.out.println(Arrays.toString(findOrder(7, new int[][]{{1, 0}, {0, 3}, {0, 2}, {3, 2}, {2, 5}, {4, 5}, {5, 6}, {2, 4}})));

    }

}
