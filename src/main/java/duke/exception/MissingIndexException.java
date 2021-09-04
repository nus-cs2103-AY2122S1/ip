package duke.exception;

import duke.Ui.UserCommands;

/**
 * Represents an exception caused by user not providing an index for delete command.
 */
public class MissingIndexException extends DukeException {
    /**
     * Constructor for MissingIndexException.
     *
     * @param userCommand Command String for which index is missing.
     */
    public MissingIndexException(UserCommands userCommand) {
        super("An index must be provided for " + userCommand.getCommand());
    }
}
