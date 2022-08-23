package amazon;

import org.junit.Test;

import java.util.*;

public class LRUCacheOA {


    class LRUCache {

        Map<Integer, Integer> map = new HashMap<>();

        LinkedHashSet<Integer> queue = new LinkedHashSet<>();


        int maxSize;

        public LRUCache(int capacity) {
            maxSize = capacity;
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                queue.remove(key);
                queue.add(key);
                return map.get(key);
            }
            return -1;
        }

        public void put(int key, int value) {
            if (map.size() == maxSize && !map.containsKey(key)) {
                var toRemove = queue.iterator().next();
                queue.remove(toRemove);
                map.remove(toRemove);
            }
            map.put(key, value);
            queue.remove(key);
            queue.add(key);
        }
    }


    @Test
    public void test() {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        lRUCache.get(1);    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lRUCache.get(2);    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lRUCache.get(1);    // return -1 (not found)
        lRUCache.get(3);    // return 3
        lRUCache.get(4);    // return 4
    }
}
