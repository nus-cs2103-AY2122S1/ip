package exceptions;

import duke.Ui;

/**
 * Duke has the ability for users to attach a specific date object to an event rather than
 * just using a string. This requires the user to provide the required date as a properly
 * formatted String. In the cases when the user does not, this checked exception will be thrown
 * to indicate to the user that he has entered an invalid date to Duke.
 */
public class DukeInvalidDateException extends Exception {

    /**
     * Creates a DukeInvalidDateException with an error message telling the user that he has
     * entered an invalid date and what the correct format should be.
     */
    public DukeInvalidDateException() {
        super("Invalid date format entered. Date should be formatted as:\n" + Ui.DATE_FORMAT);
    }
}
