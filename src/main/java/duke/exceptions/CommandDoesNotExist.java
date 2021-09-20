/**
 * This function encapsulates exceptions thrown by the chat bot when the user gives a command that does not exist.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke.exceptions;

public class CommandDoesNotExist extends DukeExceptions {
    public CommandDoesNotExist(String message) {
        super("Sorry! The command \"" + message + "\" doesn't exist :(\nPlease try again!");
    }
}
