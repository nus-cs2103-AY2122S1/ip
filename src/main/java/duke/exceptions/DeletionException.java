/**
 * This function encapsulates exceptions thrown by the chat bot where there is an error deleting an item.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.exceptions;

public class DeletionException extends DukeExceptions {
    public DeletionException(String start, String end) {
        super("Please input an integer in the range of " + start + " to " + end + "!");
    }
}
