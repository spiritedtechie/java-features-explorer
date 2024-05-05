package experiments;

import static java.lang.StringTemplate.STR;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

// Functional interface for Lambda functions
interface TwoArgInterface {
    public String apply(String a, String b);
}

// Immutable record class
record CustomerRecord(String name, String address) {
    CustomerRecord {
        Objects.requireNonNull(name);
    }

    CustomerRecord changeAddress(String address) {
        return new CustomerRecord(this.name, address);
    }
}

enum CustomerType {
    COMMERCIAL,
    CONSUMER
}

public class JavaFeatureExplorer {

    private String streams_dedupe(List<String> words) {
        var deduped = words.stream()
                .map(String::toLowerCase)
                .distinct()
                .collect(Collectors.joining(","));

        System.out.println("Deduped words: " + deduped);

        return deduped;
    }

    private Map<String, Long> streams_word_count(List<List<String>> words) {
        var wordCounts = words.stream()
                .flatMap(List::stream)
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(String::toString, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        ;

        System.out.println("Word counts: " + wordCounts);

        return wordCounts;
    }

    private void string_templates() {
        var visitors = 3;

        var multiline = STR."""
                Hello, there.
                Welcome to earth.
                We are glad you are here!
                You are visitor number: \{visitors}.
                Today's date is \{java.time.LocalDate.now()}
                    """;

                System.out.println(multiline);
    }

    private void helpfulNullPointers() {
        var customerRecord = new CustomerRecord("Joe Bloggs", null);
        try {
            customerRecord.address().split(" ");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private CustomerRecord record() {
        var customerRecord = new CustomerRecord("Joe Bloggs", "12 Earth Street");
        customerRecord = customerRecord.changeAddress("12 Mars Street");
        System.out.println(customerRecord);
        return customerRecord;
    }

    private void instanceOfPatternMatching(Object r) {
        if (r instanceof CustomerRecord(var name, var address)) {
            System.out.printf("Matched Customer record for: %s!%n", name);
        }
    }

    private String switchPatternMatching(Object o) {
        var message = switch (o) {
            case CustomerRecord r when r.name().contains("50 Cent") -> {
                yield "special customer: " + r;
            }
            case CustomerRecord r -> {
                yield "customer: " + r;
            }
            case String s when s.length() > 1 && s.length() < 50 -> "string: " + s;
            case String s when (s.length() == 1) -> "char: " + s;
            case Integer n -> "number: " + n;
            case null, default -> {
                yield "unknown or null";
            }
        };

        System.out.println("Pattern matched " + message);

        return message;
    }

    private void enumSwitch(CustomerType type) {
        switch (type) {
            case COMMERCIAL -> System.out.println("Commercial customer");
            case CONSUMER -> System.out.println("Consumer customer");
            case null, default -> {
                System.out.println("unknown or null");
            }
        }
    }

    public static void main(String[] args) {
        var explorer = new JavaFeatureExplorer();

        // Lambda
        TwoArgInterface myLambda = (var word1, var word2) -> word1 + word2;
        System.out.println("Joined words: " + myLambda.apply("John", "Doe"));

        // Collections and streaming APIs
        explorer.streams_dedupe(List.of("bob", "Bob", "john", "fred"));
        var names = List.of(List.of("bob", "john"), List.of("Bob", "fred", "fred"), List.of("george"));
        explorer.streams_word_count(names);

        // Record
        var record = explorer.record();

        // Pattern matching instance of
        explorer.instanceOfPatternMatching(record);
        
        // Cleaner switch syntax
        explorer.enumSwitch(CustomerType.COMMERCIAL);
        // Switch expression and complex pattern matching
        explorer.switchPatternMatching(record);
        explorer.switchPatternMatching(new CustomerRecord("50 Cent", "LA"));
        explorer.switchPatternMatching("Hello");
        explorer.switchPatternMatching("H");
        explorer.switchPatternMatching(1);

        // String templates
        explorer.string_templates();

        // Helpful nullpointers
        explorer.helpfulNullPointers();
    }

}
