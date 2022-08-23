package amazon;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class MeetingRoomII {

    @Test
    void test() {

    }

    public int minMeetingRooms(int[][] intervals) {

        SortedMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < intervals.length; i++) {
            map.merge(intervals[i][0], 1, Integer::sum);
            map.merge(intervals[i][1], -1, Integer::sum);
        }
        int count = 0;
        int max = 0;
        for (int currentMeeting : map.values()) {
            count = count + currentMeeting;
            max = Math.max(count, max);
        }
        return max;


    }
}
