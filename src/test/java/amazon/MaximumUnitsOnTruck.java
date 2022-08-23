package amazon;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MaximumUnitsOnTruck {

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, Comparator.comparingInt(o -> -o[1]));
        var current = 0;
        var count = 0;
        for (int i = 0; i < boxTypes.length; i++) {
            for (int j = 0; j < boxTypes[i][0]; j++) {

                if (count >= truckSize) {
                    break;
                } else {
                    current = current + boxTypes[i][1];
                    count++;
                }

            }
        }
        return current;

    }

    public int maximumUnits2(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, Comparator.comparingInt(o -> -o[1]));
        var current = 0;
        var count = 0;
        for (int i = 0; i < boxTypes.length; i++) {

            if (count >= truckSize) {
                break;
            }

            var multiplier = Math.min(boxTypes[i][0], truckSize - count);
            current = current + multiplier * boxTypes[i][1];
            count = count + multiplier;
        }
        return current;

    }

    @Test
    public void test() {
        maximumUnits2(new int[][]{{1, 3}, {2, 2}, {3, 1}}, 4);
    }

}
