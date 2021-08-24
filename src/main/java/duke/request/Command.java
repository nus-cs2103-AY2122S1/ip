package duke.request;

import duke.exception.UserException;

/**
 * A duke.request.Command represents a string in a duke.request.Request that instructs the application to perform an Action.
 */
public enum Command {
    BYE("bye"),
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo");

    /**
     * The name of the duke.request.Command.
     */
    private final String name;

    /**
     * Creates a duke.request.Command with the specified name.
     * @param name Name of the duke.request.Command.
     */
    Command(String name) {
        this.name = name;
    }

    /**
     * Parses the input String to determine the related duke.request.Command.
     * @param commandString The input command String.
     * @return The duke.request.Command related to the command String.
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
