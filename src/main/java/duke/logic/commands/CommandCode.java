package duke.logic.commands;

/**
 * Represents the command codes available.
 */
public enum CommandCode {
    BYE,
    LIST,
    DONE,
    DELETE,
    UPDATE,
    TODO,
    DEADLINE,
    EVENT,
    FIND;

    /**
     * Returns the command code that matches the given string.
     * The matching process is case insensitive.
     *
     * @param s The input string.
     * @return The corresponding command code, or null if no code matches the string.
     */
    public static CommandCode getCommandCode(String s) {
        CommandCode cc;
        try {
            cc = valueOf(s.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            cc = null;
        }
        return cc;
    }
}
