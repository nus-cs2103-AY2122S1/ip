package duke.exception;

import duke.ui.Ui;

/**
 * This exception is thrown when there is
 * no command to undo.
 *
 * @author Cheong Yee Ming
 * @version Duke Level-10
 */
public class NoPreviousCommandException extends DukeException {

    /**
     * Constructor for an NoPreviousCommandException.
     *
     * @param ui Prints message with respect to user input.
     */
    public NoPreviousCommandException(Ui ui) {
        super(ui);
    }

    /**
     * Returns an error message to highlight that
     * there is no command to undo.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return super.errorMessage("There are no commands to undo.");
    }
}
