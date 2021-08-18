public enum Command {
    LIST,
    BYE,
    ADDTASK;

    public static Command evaluateInput(String input) {
        for (Command cmd : values()) {
            if (input.equalsIgnoreCase(cmd.toString())) {
                return cmd;
            }
        }
        return ADDTASK;
    }
}
