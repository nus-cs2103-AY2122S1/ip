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
     * Description parser.
     * Parses the user's input into a string containing the description of the task.
     *
     * @param input the user's input
     * @param regex the regex to delimit the input and obtain the description
     * @return the description
     */
    private static String parseToDescription(String input, String regex)  {
        String[] tokens = input.split((regex));
        return tokens[1];
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

        switch (tokens[0]) {
        case "done":
            Memory.markTaskAsDoneByIndex(tokens[1]);
            break;
        case "todo":
            Memory.add(Todo.of(parseToDescription(input, "todo ")));
            break;
        case "deadline":
            Memory.add(Deadline.of(parseToDescription(input, "deadline ")));
            break;
        case "event":
            Memory.add(Event.of(parseToDescription(input, "event ")));
            break;
        }
    }
}
