import java.util.Objects;

public class Processor {
    /**
     * Parses the user's input into a string array of tokens, delimited by whitespace.
     *
     * @param input the user's input
     * @return an array of tokens represented as strings
     */
    private static String[] tokenParser(String input) {
        return input.trim().split("\\s+");
    }

    /**
     * Processes the user's input based on the commands contained in the input string.
     *
     * @param input the user's input
     */
    public static void process(String input) {
        if (Objects.equals(input, "list")) {
            Memory.print();
            return;
        }

        String[] tokens = tokenParser(input);

        if (Objects.equals(tokens[0], "done")) {
            Memory.markTaskAsDoneByIndex(tokens[1]);
            return;
        }
        Memory.add(input);
    }
}
