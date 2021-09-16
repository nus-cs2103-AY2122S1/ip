package blue;

import java.util.Arrays;

/**
 * Helps in making sense of the user input.
 */
public class Parser {
    private static final String EMPTY_INPUT_MESSAGE = "Input is empty!";
    private static final String INVALID_COMMAND_MESSAGE = "Invalid command!";
    private static final String LIST_STRING = "list";
    private static final String TODO_STRING = "todo";
    private static final String DEADLINE_STRING = "deadline";
    private static final String EVENT_STRING = "event";
    private static final String DONE_STRING = "done";
    private static final String DELETE_STRING = "delete";
    private static final String EXIT_STRING = "bye";
    private static final String FIND_STRING = "find";
    private static final String STATS_STRING = "stats";

    static Command getCommand(String input) throws BlueException {
        if (input.length() == 0) {
            throw new BlueException(EMPTY_INPUT_MESSAGE);
        }
        String commandStr = input.split(" ")[0];
        switch (commandStr) {
        case LIST_STRING:
            return Command.LIST;
        case TODO_STRING:
            return Command.TODO;
        case DEADLINE_STRING:
            return Command.DEADLINE;
        case EVENT_STRING:
            return Command.EVENT;
        case DONE_STRING:
            return Command.DONE;
        case DELETE_STRING:
            return Command.DELETE;
        case EXIT_STRING:
            return Command.EXIT;
        case FIND_STRING:
            return Command.FIND;
        case STATS_STRING:
            return Command.STATS;
        default:
            throw new BlueException(INVALID_COMMAND_MESSAGE);
        }
    }

    /**
     * Extracts the arguments from the user input.
     *
     * @param input User input.
     * @return Extracted arguments.
     */
    public static String[] getArguments(String input) {
        assert input != null : "Input should be a String (possibly empty)";
        if (input.length() == 0) {
            return new String[]{};
        }
        String[] split = input.split(" ");
        if (split.length < 2) {
            return new String[]{};
        }
        return Arrays.copyOfRange(split, 1, split.length);
    }
}
