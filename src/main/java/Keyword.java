public enum Keyword {
    LIST ("list"),
    DONE ("done"),
    TODO ("todo"),
    DEADLINE ("deadline"),
    EVENT ("event"),
    DELETE ("delete");

    private String command;
    Keyword(String command) {
        this.command = command;
    }

    /**
     * Checks if command is valid keyword.
     *
     * @param input command
     * @return Keyword
     * @throws DukeException if command is invalid keyword.
     */
    public static Keyword checkKeyword(String input) throws DukeException {
        switch (input) {
            case "list":
                return Keyword.LIST;
            case "done":
                return Keyword.DONE;
            case "todo":
                return Keyword.TODO;
            case "deadline":
                return Keyword.DEADLINE;
            case "event":
                return Keyword.EVENT;
            case "delete":
                return Keyword.DELETE;
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

