public enum RequestType {

    LIST    ("list"),
    DONE    ("done"),
    DELETE  ("delete"),
    TODO    ("todo"),
    DEADLINE("deadline"),
    EVENT   ("event"),
    CLOSE   ("bye"),
    INVALID ("");

    private final String command;
    RequestType(String command) {
        this.command = command;
    }

    public static RequestType find(String command) {
        for(RequestType req : RequestType.values()) {
            if (command.equals(req.command)) {
                return req;
            }
        }
        return INVALID;
    }

}
