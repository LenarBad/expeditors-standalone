import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        String file = args.length == 0 ? "input.txt" : args[0];

        readLines(file).stream()
                .map(Person::new)
                .collect(Collectors.groupingBy(Person::getAddress))
                .entrySet().stream().forEach(household -> {
                    System.out.println("\nHOUSHOLD with " + household.getValue().size() + " occupants:");
                    household.getValue().stream()
                            .filter(Person::isAdult)
                            .sorted()
                            .forEach(System.out::println);
                });

    }

    private static List<String> readLines(String file) throws IOException {
        // Using try-catch just for safe closing resources in case of IOException.
        try (InputStream inputStream = new FileInputStream(new File(file));
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            return reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            throw e;
        }
    }

}
