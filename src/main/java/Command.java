public enum Command {
    LIST,
    DONE,
    DEADLINE,
    TODO,
    EVENT,
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
