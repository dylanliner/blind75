package amazon;

import org.junit.Test;

import java.util.LinkedHashMap;

public class LRUCacheOA2 {


    class LRUCache {

        final int maxSize;

        final LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();


        public LRUCache(int capacity) {
            this.maxSize = capacity;
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                var value = map.remove(key);
                map.put(key, value);
            }
            return map.getOrDefault(key, -1);
        }

        public void put(int key, int value) {

            if (map.size() + 1 > maxSize && !map.containsKey(key)) {
                map.remove(map.entrySet().iterator().next().getKey());
            }
            map.remove(key);
            map.put(key, value);

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
