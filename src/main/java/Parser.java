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
     * Description parser.
     * Parses the user's input into a string containing the description of the task.
     *
     * @param input the user's input
     * @param regex the regex to delimit the input and obtain the description
     * @return the description
     * @throws EmptyDescriptionException if the input does not contain a description
     */
    public static String descriptionParser(String input, String regex) throws EmptyDescriptionException {
        String[] tokens = input.split((regex));
        if (tokens.length < 2) {
            throw new EmptyDescriptionException();
        }
        return tokens[1];
    }
}
