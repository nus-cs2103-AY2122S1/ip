/**
 * This function encapsulates exceptions thrown by the chat bot where details are missing from a task.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.exceptions;

public class EmptyDetailsException extends DukeExceptions {
    public EmptyDetailsException(String message) {
        super("Sorry! Please include more details for a " + message + " item :(\nPlease try again!");
    }
}
