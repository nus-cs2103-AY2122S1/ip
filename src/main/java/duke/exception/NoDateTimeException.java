package duke.exception;

import duke.ui.Ui;

/**
 * This exception is thrown when
 * the date or time is missing in user input.
 *
 * @author Cheong Yee Ming
 * @version Duke Level-9
 */
public class NoDateTimeException extends DukeException {
    /**
     * Constructor for an NoDateTimeException.
     *
     * @param ui Prints message with respect to user input.
     */
    public NoDateTimeException(Ui ui) {
        super(ui);
    }

    /**
     * Returns an error message to highlight that
     * the date or time is missing in user input.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return super.errorMessage("The Date/Time of a task cannot be empty.");
    }
}
