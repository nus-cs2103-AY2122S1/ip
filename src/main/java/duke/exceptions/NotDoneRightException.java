/**
 * This function encapsulates exceptions thrown by the chat bot when commands for "done" is not entered correctly.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.exceptions;

public class NotDoneRightException extends DukeExceptions {
    public NotDoneRightException(String start, String end) {
        super("Please input an integer in the range of " + start + " to " + end + "!");
    }
}
