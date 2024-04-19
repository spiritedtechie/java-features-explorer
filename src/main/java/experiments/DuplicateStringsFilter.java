package experiments;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class DuplicateStringsFilter {

    public String deduplicate(List<String> names) {
        return names.stream()
                .distinct()
                // .map((String s) -> {
                // return s;
                // })
                .map(s -> s)
                .collect(Collectors.joining(","));
    }

    public List<String> deduplicate_2(List<List<String>> names) {
        return names.stream()
                // .flatMap((var a) -> {
                // return a.stream();
                // })
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    public Map<String, Long> wordCount(List<List<String>> names) {
        var wordCounts = names.stream()
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(String::toString, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        ;

        return wordCounts;
    }

    public String wordCountManual(String sentence) {
        var words = sentence.split(" ");
        var wordCounts = new HashMap<String, Integer>();

        // Create map of counts
        for (String word : words) {
            var count = wordCounts.getOrDefault(word, 0);
            wordCounts.put(word, count + 1);
        }

        // Sort map entry set
        List<Entry<String, Integer>> entries = new LinkedList<Entry<String, Integer>>(wordCounts.entrySet());
        entries.sort((s1, s2) -> s1.getKey().compareTo(s2.getKey()));

        // Build string
        StringBuilder result = new StringBuilder();
        for (var entry : entries) {
            result.append(entry.getKey())
                    .append("->")
                    .append(entry.getValue()).append(" ");
        }

        return result.toString().trim();
    }

}
