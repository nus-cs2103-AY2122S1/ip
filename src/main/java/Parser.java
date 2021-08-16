import java.util.Arrays;

/**
 * Utility class that handles parsing of strings to other useful data structures.
 */
public class Parser {
    /**
     * Parses the user's input into a string array of tokens, delimited by whitespace.
     *
     * @param input the user's input
     * @return an array of tokens represented as strings
     * @throws InvalidInputException if the input string is null
     */
    public static String[] tokenParser(String input) throws InvalidInputException {
        if (input == null) {
            throw new InvalidInputException();
        }
        return input.trim().split("\\s+");
    }

    /**
     * Parses the user's input into a string containing the description of the task.
     *
     * @param input the user's input
     * @param command the type of command
     * @return the description
     * @throws EmptyDescriptionException if the input does not contain a description
     */
    public static String descriptionParser(String input, Command command) throws EmptyDescriptionException {
        String[] tokens = Arrays.stream(input.trim()
                .split((command.toString())))
                .map(String::trim)
                .toArray(String[]::new);
        if (tokens.length < 2) {
            throw new EmptyDescriptionException();
        }
        return tokens[1];
    }
}
