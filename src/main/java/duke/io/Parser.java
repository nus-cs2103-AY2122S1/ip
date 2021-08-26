package duke.io;

/**
 * The {@code Parser} class handles command parsing.
 */
public class Parser {
    private final String COMMAND_MAKE_TASK = "COMMAND_MAKE_TASK";
    private final String COMMAND_LIST_TASKS = "COMMAND_LIST_TASKS";
    private final String COMMAND_QUIT = "COMMAND_QUIT";
    private final String COMMAND_MARK_TASK_STATUS = "COMMAND_MARK_TASK_STATUS";
    private final String COMMAND_DELETE_TASK = "COMMAND_DELETE_TASK";
    private final String COMMAND_UNKNOWN = "COMMAND_UNKNOWN";

    public Parser() { }

    /**
     * Converts the provided user {@code String input} and parses the desired command.
     *
     * @param input {@code String} of user input.
     * @return {@code String} which corresponds to the command that the user entered.
     */
    public String parse(String input) {

        if (input.equals("bye") || input.equals("exit") || input.equals("quit")) {
            return COMMAND_QUIT;
        } else if (input.equals("list") || input.equals("ls")) {
            return COMMAND_LIST_TASKS;
        } else if (input.length() >= 4 && input.startsWith("done")) {
            return COMMAND_MARK_TASK_STATUS;
        } else if (input.length() >= 6 && input.startsWith("delete")) {
            return COMMAND_DELETE_TASK;
        } else if (input.startsWith("event") || input.startsWith("deadline") || input.startsWith("todo")) {
            return COMMAND_MAKE_TASK;
        } else {
            return COMMAND_UNKNOWN;
        }
    }


}
