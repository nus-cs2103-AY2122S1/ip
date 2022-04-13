package duke;

/**
 * An enum of all available commands + invalid command.
 */
public enum Command {
    LIST("list", "ls"),
    FIND("find", "f"),
    DONE("done", "d"),
    DELETE("delete", "rm"),
    CLEAR("clear", "clr"),
    TODO("todo", "td"),
    DEADLINE("deadline", "dl"),
    EVENT("event", "ev"),
    BYE("bye", "quit"),
    INVALID("invalid", "invalid");

    private String c;
    private String abbr;

    Command(String c, String abbr) {
        this.c = c;
        this.abbr = abbr;
    }

    /**
     * Converts a String to a Command while ignoring case.
     *
     * @param text The String to be converted.
     * @return The corresponding Command.
     */
    public static Command cmdFromString(String text) {
        for (Command cmd : Command.values()) {
            if (cmd.c.equalsIgnoreCase(text) || cmd.abbr.equalsIgnoreCase(text)) {
                return cmd;
            }
        }
        return Command.INVALID;
    }
}
