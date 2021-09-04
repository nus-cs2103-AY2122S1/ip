package duke.exception;

import duke.Ui.UserCommands;

/**
 * Represents an exception caused by user providing a non-number index.
 */
public class InvalidNumberInputException extends DukeException {
    /**
     * Constructor for InvalidNumberInputException.
     *
     * @param userCommand UserCommand for which user provided a non-number input
     */
    public InvalidNumberInputException(UserCommands userCommand) {
        super("Index for " + userCommand.getCommand() + " must be an integer.");
    }
}
