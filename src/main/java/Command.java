/**
 * A Command represents a string in a Request that instructs the application to perform an Action.
 */
public enum Command {
    BYE("bye"),
    LIST("list"),
    DONE("done"),
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo");

    /**
     * The name of the Command.
     */
    private final String name;

    /**
     * Creates a Command with the specified name.
     * @param name Name of the Command.
     */
    Command(String name) {
        this.name = name;
    }

    /**
     * Parses the input String to determine the related Command.
     * @param commandString The input command String.
     * @return The Command related to the command String.
     * @throws UserException If the command String is invalid.
     */
    public static Command parseFrom(String commandString) throws UserException {
        for (Command command : Command.values()) {
            if (command.name.equals(commandString)) {
                return command;
            }
        }

        throw new UserException("I'm sorry, but I don't know what that means :-(");
    }
}
