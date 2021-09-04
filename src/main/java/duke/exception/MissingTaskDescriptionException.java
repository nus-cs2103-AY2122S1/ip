package duke.exception;

import duke.Ui.UserCommands;

/**
 * Represents an exception caused by the user not providing a description for their tasks.
 */
public class MissingTaskDescriptionException extends DukeException {
    /**
     * Constructor for MissingTaskDescriptionException.
     *
     * @param userCommand Command String for which description is missing.
     */
    public MissingTaskDescriptionException(UserCommands userCommand) {
        super("The description of " + userCommand.getCommand() + " cannot be empty.");
    }
}
