package duke.exception;

/**
 * Thrown when the list is empty.
 *
 * @author Zhang Shi Chen
 */
public class EmptyListException extends DukeException {
    public EmptyListException() {
        super("Your list is empty! Maybe add some tasks into it?");
    }
}
