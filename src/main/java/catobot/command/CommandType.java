package catobot.command;

/**
 * Represents the types of commands.
 */
public enum CommandType {

    CLOSE("bye"),
    DEADLINE("deadline"),
    DELETE("delete"),
    DONE("done"),
    EVENT("event"),
    FIND("find"),
    INVALID(""),
    LIST("list"),
    SORT("sort"),
    TODO("todo");

    /** Content of the command. */
    private final String command;

    /**
     * Constructor for a command.
     *
     * @param command The content of the command.
     */
    CommandType(String command) {
        this.command = command;
    }

    /**
     * Finds the matching Command of a request.
     *
     * @param request The request from raw input line.
     * @return The corresponding Command to a request.
     */
    public static CommandType find(String request) {
        String command = request.split(" ")[0];

        for (CommandType req : CommandType.values()) {
            if (command.equals(req.command)) {
                return req;
            }
        }
        return INVALID;
    }

    public String getValue() {
        return command;
    }

}
