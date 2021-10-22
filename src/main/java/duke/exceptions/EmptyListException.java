package duke.exceptions;

/**
 * Encapsulates the EmptyListException thrown by the Duke bot.
 */
public class EmptyListException extends DukeException {
    /**
     * Constructor for EmptyListException.
     */
    public EmptyListException() {
        super("Sorry! Your list is empty!");
    }
}
