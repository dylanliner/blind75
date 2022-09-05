package amazon;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MovingAverages {

    class MovingAverage {

        int windowSize = 0;

        List<Integer> preFixSum = new ArrayList<>();

        public MovingAverage(int size) {
            this.preFixSum.add(0);
            this.windowSize = size;
        }

        public double next(int val) {
            preFixSum.add(preFixSum.get(preFixSum.size() - 1) + val);
            return (double) (preFixSum.get(preFixSum.size() - 1) - preFixSum.get(preFixSum.size() - 1 - Math.min(windowSize, preFixSum.size() - 1))) / Math.min(windowSize, preFixSum.size() - 1);


        }

    }

    @Test
    public void test() {
        MovingAverage movingAverage = new MovingAverage(3);
        System.out.println(movingAverage.next(1)); // return 1.0 = 1 / 1
        System.out.println(movingAverage.next(10)); // return 5.5 = (1 + 10) / 2
        System.out.println(movingAverage.next(3)); // return 4.66667 = (1 + 10 + 3) / 3
        System.out.println(movingAverage.next(5)); // return 6.0 = (10 + 3 + 5) / 3
    }


}
