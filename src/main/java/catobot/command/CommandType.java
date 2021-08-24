package catobot.command;

public enum CommandType {

    LIST    ("list"),
    DONE    ("done"),
    DELETE  ("delete"),
    FIND    ("find"),
    TODO    ("todo"),
    DEADLINE("deadline"),
    EVENT   ("event"),
    CLOSE   ("bye"),
    INVALID ("");

    private final String command;
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

    public String getValue() {
        return command;
    }

}
