/**
 * This function encapsulates exceptions thrown by the chat bot where there is an error with the user date
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.exceptions;

public class DateNotAcceptedException extends DukeExceptions {

    /**
     * Constructs a DateNotAcceptedException.
     *
     * @param message Message shown by the exception.
     */
    public DateNotAcceptedException(String message) {
        super("There was an error with the Date inputted. Please recheck and input in the form "
                + "\"YYYY-MM-DD HH:MM\" for Deadlines or  \"YYYY-MM-DD HH:MM~HH:MM\" for Events!\n" + message);
    }
}
