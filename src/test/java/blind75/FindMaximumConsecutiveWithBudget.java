package blind75;

import org.junit.jupiter.api.Test;

public class FindMaximumConsecutiveWithBudget {

    @Test
    public void test() {
        var maxConsecutive = 0;
        var numbers = new int[]{3, 3, 2, 1, 1, 1, 1, 5};
        var money = 5;
        for (int i = 0; i < numbers.length; i++) {

            int j = 0;
            int moneyAmount = 0;
            for (j = i; moneyAmount < money; j++) {
                moneyAmount = moneyAmount + numbers[j];
            }
            maxConsecutive = Math.max(maxConsecutive, j - i - 1);
        }

        System.out.println(maxConsecutive);

    }
}
