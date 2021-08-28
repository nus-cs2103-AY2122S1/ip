package duke.command;

/**
 * Represents a fixed list of supported user commands.
 *
 * @author kevin9foong
 */
public enum CommandType {
    LIST, TODO, DEADLINE, EVENT, DELETE, DONE, FIND, BYE;

    /**
     * Returns Command classification based on the given string.
     *
     * @param commandText string which matches command name.
     * @return Command representing the given string command, or null if no command matched.
     */
    public static CommandType getCommand(String commandText) {
        for (CommandType cmd : CommandType.values()) {
            if (commandText.toUpperCase().equals(cmd.toString())) {
                return cmd;
            }
        }
        return null;
    }
}
