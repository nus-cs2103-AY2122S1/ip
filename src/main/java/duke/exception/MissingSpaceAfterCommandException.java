package duke.exception;

import duke.Ui.UserCommands;

/**
 * Represents an exception caused by user not providing a space after UserCommand.
 */
public class MissingSpaceAfterCommandException extends DukeException {
    /**
     * Constructor for MissingSpaceAfterCommandException.
     *
     * @param userCommand UserCommand for which space is missing after it.
     */
    public MissingSpaceAfterCommandException(UserCommands userCommand) {
        super("There is a missing space after " + userCommand.getCommand() + ".");
    }
}
