package duke.exception;

import duke.ui.Ui;

/**
 * This exception is thrown when
 * an invalid command is input.
 *
 * @author Cheong Yee Ming
 * @version Duke Level-9
 */
public class NoSuchCommandException extends DukeException {
    /**
     * Constructor for an NoSuchCommandException.
     *
     * @param ui Prints message with respect to user input.
     */
    public NoSuchCommandException(Ui ui) {
        super(ui);
    }

    /**
     * Returns an error message to highlight that
     * user input is not recognised.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return super.errorMessage("I'm sorry, but I don't know what that means :-(");
    }
}
