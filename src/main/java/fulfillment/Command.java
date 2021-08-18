package fulfillment;

public enum Command {
    LIST, TODO, DEADLINE, EVENT, DELETE, DONE, BYE;

    /**
     * Returns Command classification based on the given string.
     * @param commandText string which matches command name
     * @return Command representing the given string command, or null if no command matched.
     */
    public static Command getCommand(String commandText) {
        for (Command cmd : Command.values()) {
            if (commandText.toUpperCase().equals(cmd.toString())) {
                return cmd;
            }
        }
        return null;
    }
}
