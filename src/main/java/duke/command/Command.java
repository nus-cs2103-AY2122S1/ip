package duke.command;

import duke.util.DukeException;

/**
 * Represents all the first words typed into the input for Duke
 *
 */
public enum Command {
    BYE,
    LIST,
    DONE,
    DELETE,
    DEADLINE,
    EVENT,
    TODO;

    private static final String INCOHERENT_INPUT_MESSAGE = "I'm sorry, but I don't know what that means :-(";

    /**
     * Method to create a command, throwing an error if it's not part of the enums
     *
     * @param commandString The first word in the input
     * @return A command representing one of the enums above
     * @throws DukeException An exception thrown according to the message given
     */
    public static Command initialiseCommand(String commandString) throws DukeException {
        try {
            return Command.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException err) {
            throw new DukeException(INCOHERENT_INPUT_MESSAGE);
        }
    }
}
