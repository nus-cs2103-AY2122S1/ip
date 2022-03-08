package duke.exception;

/**
 * This class encapsulates exception due to invalid task index.
 *
 * @author Teo Sin Yee
 */
public class DukeNoSuchTaskException extends DukeException {
    /**
     * Constructor for DukeNoSuchTaskException.
     */
    public DukeNoSuchTaskException() {
        super("Error: Task doesn't exist! :o Check your index input ~");
    }
}