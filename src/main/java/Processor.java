public class Processor {
    /**
     * Parses the user's input into a string array of tokens, delimited by whitespace.
     *
     * @param input the user's input
     * @return an array of tokens represented as strings
     * @throws InvalidInputException if the input string is null
     */
    private static String[] tokenParser(String input) throws InvalidInputException {
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
    private static String parseToDescription(String input, String regex) throws EmptyDescriptionException {
        String[] tokens = input.split((regex));
        if (tokens.length < 2) {
            throw new EmptyDescriptionException();
        }
        return tokens[1];
    }

    /**
     * Processes the user's input based on the commands contained in the input string.
     *
     * @param input the user's input
     * @throws InvalidInputException if the user's input is not a valid input
     */
    public static void process(String input) throws InvalidInputException {
        String[] tokens = tokenParser(input);
        int len = tokens.length;

        if (len == 0) {
            throw new EmptyCommandException();
        }

        String command = tokens[0];

        switch (Command.fromString(command)) {
        case LIST:
            if (len > 1) {
                throw new BadInputFormatException();
            }
            Memory.print();
            break;
        case DONE:
            Memory.markTaskAsDoneByIndex(tokens[1]);
            break;
        case DELETE:
            Memory.deleteTaskByIndex(tokens[1]);
            break;
        case TODO:
            Memory.add(Todo.of(parseToDescription(input, "todo ")));
            break;
        case DEADLINE:
            Memory.add(Deadline.of(parseToDescription(input, "deadline ")));
            break;
        case EVENT:
            Memory.add(Event.of(parseToDescription(input, "event ")));
            break;
        default:
            throw new UnknownCommandException();
        }
    }
}
