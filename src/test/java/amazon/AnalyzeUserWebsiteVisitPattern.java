package amazon;

import org.junit.jupiter.api.Test;

import java.util.*;

public class AnalyzeUserWebsiteVisitPattern {

    private int max = 0;

    private String[] ans = new String[]{};

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {

        var map = new HashMap<String, TreeMap<Integer, String>>();

        for (int i = 0; i < username.length; i++) {
            map.computeIfAbsent(username[i], s -> new TreeMap<>()).put(timestamp[i], website[i]);
        }

        var patternCountMap = new HashMap<List<String>, Integer>();

        map.forEach((k, v) ->
                generateAllPatternsForUser(new ArrayList<>(v.values())).forEach(p -> {
                    patternCountMap.merge(p, 1, Integer::sum);
                    var pattern = p.toArray(new String[0]);
                    if (patternCountMap.get(p) > max) {
                        ans = pattern;
                        max = patternCountMap.get(p);
                    } else if (max == patternCountMap.get(p) && Arrays.compare(ans, pattern) > 0) {
                        ans = pattern;
                    }

                })
        );

        return List.of(ans);

    }

    private HashSet<List<String>> generateAllPatternsForUser(List<String> websites) {
        var patterns = new HashSet<List<String>>();
        for (int i = 0; i < websites.size() - 2; i++) {
            for (int j = i + 1; j < websites.size(); j++) {
                for (int k = j + 1; k < websites.size(); k++) {
                    patterns.add(List.of(websites.get(i), websites.get(j), websites.get(k)));
                }
            }
        }
        return patterns;
    }

    @Test
    public void test() {
        System.out.println(mostVisitedPattern(new String[]{"joe", "joe", "joe", "james", "james", "james", "james", "mary", "mary", "mary"},
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, new String[]{"home", "about", "career", "home", "cart", "maps", "home", "home", "about", "career"}));
    }

}
