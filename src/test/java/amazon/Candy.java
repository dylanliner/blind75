package amazon;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Candy {

    public int candy(int[] ratings) {

        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);

        if (ratings.length == 1) {
            return 1;
        }

        if (ratings[0] < ratings[1]) {
            candies[1] = 2;
        }

        for (int i = 1; i < ratings.length; i++) {
            var j = i;
            while (j > 0 && ratings[j - 1] > ratings[j]) {
                candies[j - 1] = Math.max(candies[j - 1], candies[j] + 1);
                j--;
            }
            var k = i;
            while (k < ratings.length - 1 && ratings[k] < ratings[k + 1]) {
                candies[k + 1] = candies[k] + 1;
                k++;
            }

        }
        return Arrays.stream(candies).sum();

    }

    @Test
    public void test() {
        //System.out.println(candy(new int[]{1, 5, 4, 3, 2, 5, 2, 1}));
        System.out.println(candy(new int[]{1, 0, 2}));
        System.out.println(candy(new int[]{1, 2, 2}));
        System.out.println(candy(new int[]{1, 3, 2, 2, 1}));
        System.out.println(candy(new int[]{1, 3, 4, 5, 2}));
    }
}
