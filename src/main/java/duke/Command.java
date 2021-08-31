package duke;

/**
 * An enum of all available commands + invalid command.
 */
public enum Command {
    LIST("list"),
    FIND("find"),
    DONE("done"),
    DELETE("delete"),
    CLEAR("clear"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    BYE("bye"),
    INVALID("invalid");

    private String c;

    Command(String c) {
        this.c = c;
    }

    /**
     * Converts a String to a Command while ignoring case.
     *
     * @param text The String to be converted.
     * @return The corresponding Command.
     */
    public static Command fromString(String text) {
        for (Command cmd : Command.values()) {
            if (cmd.c.equalsIgnoreCase(text)) {
                return cmd;
            }
        }
        return Command.INVALID;
    }
}
