package experiments;

import java.util.List;
import java.util.Map;
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

    public Map<String, Long> groupCount(List<List<String>> names) {
        var collect = names.stream()
                // .flatMap((var a) -> {
                // return a.stream();
                // })
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(String::toString, Collectors.counting()));

        return collect;
    }

}
