package amazon;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class AmazonOA {

    @Test
    public void test() {
        System.out.println(getMinimumDays2(List.of(9, 0, 0, 0, 3, 0, 3, 0)));
        System.out.println(getMinimumDays2(List.of(4, 2, 3, 4)));
        //System.out.println(findMaxProducts(List.of(7, 4, 5, 2, 6, 5)));
    }

    public static int getMinimumDays(List<Integer> parcels) {
        // Write your code here
        //find Minimum in parcels
        //substract minimum from every parcel
        int days = 0;
        boolean hasParcels = parcels.stream().anyMatch(v -> v > 0);
        parcels = parcels.stream().filter(v -> v != 0).collect(Collectors.toList());
        while (hasParcels) {
            int min = parcels.stream().mapToInt(v -> v).min().getAsInt();
            parcels = parcels.stream().map(v -> v - min).filter(v -> v != 0).collect(Collectors.toList());
            hasParcels = parcels.stream().anyMatch(v -> v > 0);
            days++;
        }
        return days;

    }

    public static int getMinimumDays2(List<Integer> parcels) {
        // Write your code here
        //find Minimum in parcels
        //substract minimum from every parcel
        parcels = parcels.stream().filter(v -> v != 0).collect(Collectors.toList());
        Set<Integer> set = new HashSet<>(parcels);
        PriorityQueue<Integer> pq = new PriorityQueue<>(set);
        int days = 0;
        while (!pq.isEmpty()) {
            pq.remove();
            days++;
        }
        return days;

    }


    public static long findMaxProducts(List<Integer> products) {
        // Write your code here
        //Find all sub arrays possible
        long max = 0;
        for (int i = 0; i < products.size(); i++) {
            long subArraySum = products.get(i);
            long prev = subArraySum;
            for (int j = i - 1; j >= 0; j--) {
                subArraySum = subArraySum + Math.min(products.get(j), prev - 1);
                prev = Math.min(products.get(j), prev - 1);
                max = Math.max(max, subArraySum);
            }
            max = Math.max(max, subArraySum);
        }
        return max;
    }


}
