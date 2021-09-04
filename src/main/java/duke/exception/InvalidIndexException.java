package duke.exception;

import duke.Ui.UserCommands;

/**
 * Represents an exception caused by user providing an invalid index.
 */
public class InvalidIndexException extends DukeException {
    /**
     * Constructor for InvalidIndexException.
     *
     * @param userCommand UserCommand for which index provided is invalid.
     */
    public InvalidIndexException(UserCommands userCommand) {
        super("Index provided for " + userCommand.getCommand()
                + " is either less than 1 or exceeds the length of the list, hence invalid.");
    }
}
