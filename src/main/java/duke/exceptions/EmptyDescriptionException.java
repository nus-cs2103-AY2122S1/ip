/**
 * This function encapsulates exceptions thrown by the chat bot where the description for a task is empty.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.exceptions;

public class EmptyDescriptionException extends DukeExceptions {
    public EmptyDescriptionException(String message) {
        super("Sorry! There needs to be a description for a " + message + " item :(\nPlease try again!");
    }
}
