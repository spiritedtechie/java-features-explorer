package experiments;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class DuplicateStringsFilterTest {

    private final DuplicateStringsFilter filter = new DuplicateStringsFilter();

    @Test
    public void testDeduplication() {

        List<String> names = List.of("bob", "john", "bob", "fred", "fred", "george");

        String result = filter.deduplicate(names);

        assertEquals("bob,john,fred,george", result);
    };

    @Test
    public void testDeduplicationListOfLists() {

        List<List<String>> names = List.of(List.of("bob", "john"), List.of("bob", "fred", "fred"), List.of("george"));

        List<String> result = filter.deduplicate_2(names);

        assertEquals(List.of("bob", "john", "fred", "george"), result);
    }

    @Test
    public void testGroupCount() {

        List<List<String>> names = List.of(List.of("bob", "john"), List.of("bob", "fred", "fred"), List.of("george"));

        Map<String, Long> result = filter.groupCount(names);

        Map<String, Long> expected = Map.of("bob", 2L, "john", 1L, "fred", 2L, "george", 1L);

        assertEquals(expected, result);
    }
}
