package spiritedtechie.experiments;

import static java.lang.StringTemplate.STR;

import java.util.Objects;

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

    public void string_templates() {
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

    public void helpfulNullPointers() {
        var customerRecord = new CustomerRecord("Joe Bloggs", null);
        customerRecord.address().split(" ");
    }

    public CustomerRecord record() {
        var customerRecord = new CustomerRecord("Joe Bloggs", "12 Earth Street");
        customerRecord = customerRecord.changeAddress("12 Mars Street");
        System.out.println(customerRecord);
        return customerRecord;
    }

    public void instanceOfPatternMatching(Object r) {
        if (r instanceof CustomerRecord(var name, var address)) {
            System.out.printf("Matched Customer record for: %s!%n", name);
        }
    }

    public void switchPatternMatching(Object o) {
        String message = switch (o) {
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
    }

    public void enumSwitch(CustomerType type) {
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

        explorer.string_templates();

        var record = explorer.record();

        explorer.instanceOfPatternMatching(record);
        explorer.switchPatternMatching(record);
        explorer.switchPatternMatching(new CustomerRecord("50 Cent", "LA"));
        explorer.switchPatternMatching("Hello");
        explorer.switchPatternMatching("H");
        explorer.switchPatternMatching(1);

        explorer.enumSwitch(CustomerType.COMMERCIAL);

        try {
            explorer.helpfulNullPointers();
        } catch (Exception e) {
            System.out.println(e);
        }

        Runnable runnable = () -> System.out.println("Hello world!");
        runnable.run();
    }

}
