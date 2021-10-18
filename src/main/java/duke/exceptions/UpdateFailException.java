package duke.exceptions;

/**
 * Represents an exception for the chat bot.
 * Thrown when chat bot cannot update the task specified by user.
 */
public class UpdateFailException extends DukeException {
    public UpdateFailException() {
        super("Sorry, I am unable to update it. Did you try to update the deadline of a Todo Task?");
    }
}
