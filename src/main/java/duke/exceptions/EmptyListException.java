package duke.exceptions;

/**
 * This is an EmptyListException class that extends DukeException.
 * This exception is thrown when the user wants to see the list when it is empty.
 */
public class EmptyListException extends DukeException {

    /**
     * This is the EmptyListException constructor.
     */
    public EmptyListException() {
        super("The list is empty!");
    }

}
