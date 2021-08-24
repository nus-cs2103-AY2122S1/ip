public enum Commands {
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    BYE("bye"),
    INVALID("invalid");

    private String c;
    Commands(String c) {
        this.c = c;
    }

    public static Commands fromString(String text) {
        for (Commands cmd : Commands.values()) {
            if (cmd.c.equalsIgnoreCase(text)) {
                return cmd;
            }
        }
        return Commands.INVALID;
    }
}
