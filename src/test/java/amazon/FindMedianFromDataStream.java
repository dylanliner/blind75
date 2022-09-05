package amazon;

import org.junit.jupiter.api.Test;

import java.util.*;

public class FindMedianFromDataStream {

    class MedianFinder2 {

        TreeMap<Double, Integer> treeMap = new TreeMap<>();
        int size = 0;

        public MedianFinder2() {

        }

        public void addNum(int num) {
            treeMap.merge((double) num, 1, Integer::sum);
            size = size + 1;

        }

        public double findMedian() {
            var list = new ArrayList<Double>();
            treeMap.forEach((k, v) -> {
                for (int i = 0; i < v; i++) {
                    list.add(k);
                }
            });
            if (size % 2 != 0) {
                return list.get(size / 2);

            } else {
                return (list.get(size / 2 - 1) + list.get(size / 2)) / 2;
            }
        }
    }

    class MedianFinder {

        PriorityQueue<Double> large = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Double> small = new PriorityQueue<>();

        boolean isSameSize = true;

        public MedianFinder() {

        }

        public void addNum(int num) {
            if (isSameSize) {
                large.add((double) num);
                small.add(large.poll());
            } else {
                small.add((double) num);
                large.add(small.poll());
            }
            isSameSize = !isSameSize;
        }

        public double findMedian() {
            if (!isSameSize) {
                return small.peek();
            } else {
                return (large.peek() + small.peek()) / 2.0;
            }

        }
    }

    @Test
    public void test() {
        MedianFinder medianFinder = new MedianFinder();
        //medianFinder.addNum(1);    // arr = [1]
        //medianFinder.addNum(2);    // arr = [1, 2]
        //System.out.println(medianFinder.findMedian()); // return 1.5 (i.e., (1 + 2) / 2)
        //medianFinder.addNum(3);    // arr[1, 2, 3]
        //System.out.println(medianFinder.findMedian()); // return 2.0
        // if simple sort nlogn every time I do find operation;
        //if pq n/2 logn for find and log n to insert
        //if treeMap , 1 to insert, n/2 to find
        //668877
        //46661

        medianFinder = new MedianFinder();
        medianFinder.addNum(6);
        System.out.println(medianFinder.findMedian());// arr = [1]
        medianFinder.addNum(10);
        System.out.println(medianFinder.findMedian());// arr = [1, 2]
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());// arr = [1, 2]
        medianFinder.addNum(6);
        System.out.println(medianFinder.findMedian());// arr = [1, 2]
        medianFinder.addNum(5);
        System.out.println(medianFinder.findMedian());// arr = [1, 2]
        medianFinder.addNum(0);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(6);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(1);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(0);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(0);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);    // arr[1, 2, 3]
        System.out.println(medianFinder.findMedian());
    }
}
