package spiritedtechie.experiments;

import java.util.stream.Collectors;
import java.util.*;

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
                // .flatMap((List<String> a) -> {
                // return a.stream();
                // })
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    public Map<String, Long> groupCount(List<List<String>> names) {
         var collect = names.stream()
                // .flatMap((List<String> a) -> {
                // return a.stream();
                // })
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(String::toString, Collectors.counting()));

        return collect;
    }

}
