package duke.command;

/** Enum for command types. */
public enum Commands {
    TODO,
    EVENT,
    DEADLINE,
    BYE,
    DONE,
    DELETE,
    EXIT,
    FIND,
    LIST,
    MARK,
    PRIORITIZE;

    /**
     * Factory method for Commands enum. Returns null if there is no corresponding command.
     * @param cmd The command in string type.
     * @return Commands enum.
     */
    public static Commands of(String cmd) {
        for (Commands command : Commands.values()) {
            if (cmd.equalsIgnoreCase(command.toString())) {
                return command;
            }
        }

        return null;
    }
}
