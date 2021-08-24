package catobot.command;

/**
 * Represents the types of commands.
 */
public enum CommandType {

    LIST    ("list"),
    DONE    ("done"),
    DELETE  ("delete"),
    TODO    ("todo"),
    DEADLINE("deadline"),
    EVENT   ("event"),
    CLOSE   ("bye"),
    INVALID ("");

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
        for(CommandType req : CommandType.values()) {
            if (command.equals(req.command)) {
                return req;
            }
        }
        return INVALID;
    }

}
