package duke.exception;

import duke.ui.Ui;

/**
 * This exception is thrown when
 * an invalid task description is missing.
 *
 * @author Cheong Yee Ming
 * @version Duke Level-9
 */
public class NoTaskDescriptionException extends DukeException {
    /**
     * Constructor for an NoSuchCommandException.
     *
     * @param ui Prints message with respect to user input.
     */
    public NoTaskDescriptionException(Ui ui) {
        super(ui);
    }

    /**
     * Returns an error message to highlight that
     * task description is missing.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return super.errorMessage("The description of a task cannot be empty.");
    }
}