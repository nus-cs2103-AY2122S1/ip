public enum Command {

    LIST    ("list"),
    DONE    ("done"),
    DELETE  ("delete"),
    TODO    ("todo"),
    DEADLINE("deadline"),
    EVENT   ("event"),
    CLOSE   ("bye"),
    INVALID ("");

    private final String command;
    Command(String command) {
        this.command = command;
    }

    /**
     * Find the matching Command of a request.
     * @param request The request from raw input line.
     * @return The corresponding Command to a request.
     */
    public static Command find(String request) {
        String command = request.split(" ")[0];
        for(Command req : Command.values()) {
            if (command.equals(req.command)) {
                return req;
            }
        }
        return INVALID;
    }

}
