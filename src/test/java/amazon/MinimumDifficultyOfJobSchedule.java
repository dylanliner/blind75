package amazon;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinimumDifficultyOfJobSchedule {


    @Test
    public void test() {
        //minDifficulty(new int[]{3, 2, 1}, 2);
        minDifficulty(new int[]{6, 5, 4, 3, 2, 1}, 4);

    }

    public int minDifficulty(int[] jobDifficulty, int d) {

        //find all ways of spliting in d arrays
        //find max of every array and add them up

        //654 3 2 1 x
        //6 543 2 1 x
        //6 5 432 1 x
        //6 5 4 321 x

        //65 4 3 21 x
        //6 54 32 1 x
        //65 43 2 1 x
        //6 5 43 21 x
        //6 54 2 21 x
        //65 4 32 1 x

        var possibleSolutions = new HashSet<List<List<Integer>>>();
        recursive(0, true, new ArrayList<>(), jobDifficulty, new ArrayList<List<Integer>>(), possibleSolutions, d);
        System.out.println(possibleSolutions);
        System.out.println(possibleSolutions.size());
        return 0;

    }

    public void recursive(int i, boolean isSameDay, List<Integer> dayDifficulties, int[] jobDifficulty, List<List<Integer>> days, Set<List<List<Integer>>> possibleSolutions, int d) {

        if (i == jobDifficulty.length) {
            if (!dayDifficulties.isEmpty()) {
                days.add(dayDifficulties);
            }
            if (days.size() == d) {
                possibleSolutions.add(days);
            }

            return;
        }
        if (isSameDay) {
            dayDifficulties.add(jobDifficulty[i]);
            recursive(i + 1, true, new ArrayList<>(dayDifficulties), jobDifficulty, new ArrayList<>(days), possibleSolutions, d);
            recursive(i + 1, false, new ArrayList<>(dayDifficulties), jobDifficulty, new ArrayList<>(days), possibleSolutions, d);
        } else {
            days.add(dayDifficulties);
            var newDayDifficulties = new ArrayList<Integer>(List.of(jobDifficulty[i]));
            recursive(i + 1, true, newDayDifficulties, jobDifficulty, new ArrayList<>(days), possibleSolutions, d);
            recursive(i + 1, false, newDayDifficulties, jobDifficulty, new ArrayList<>(days), possibleSolutions, d);
        }


    }
}
