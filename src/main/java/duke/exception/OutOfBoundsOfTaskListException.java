package duke.exception;

/**
 * Represents a DukeException that is thrown when the index of the TaskList specified is out of bounds.
 */
public class OutOfBoundsOfTaskListException extends DukeException {

    /**
     * Constructs an OutOfBoundsTaskListException.
     */
    public OutOfBoundsOfTaskListException() {
        super("Meow! What are you talking about? There is no task under that number!");
    }
}
