public enum Command {
    LIST,
    DONE,
    DEADLINE,
    TODO,
    EVENT,
    DELETE,
    BYE,
    INVALID;

    public static Command evaluateInput(String input) {
        for (Command cmd : values()) {
            if (input.equalsIgnoreCase(cmd.toString())) {
                return cmd;
            }
        }
        return INVALID;
    }
}
