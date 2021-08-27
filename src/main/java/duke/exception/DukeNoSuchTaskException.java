package duke.exception;

/**
 * This class encapsulates exception due to invalid task index.
 *
 * @author Teo Sin Yee
 */
public class DukeNoSuchTaskException extends DukeException {
    public DukeNoSuchTaskException() {
        super("Task doesn't exist! :o Check your index input ~");
    }
}