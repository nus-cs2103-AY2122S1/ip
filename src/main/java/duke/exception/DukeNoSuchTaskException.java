package duke.exception;

/**
 * Exception for task not found in Duke program.
 *
 * @author Chng Zi Hao
 */
public class DukeNoSuchTaskException extends DukeException {
    /**
     * Constructor for DukeNoSuchTaskException.
     */
    public DukeNoSuchTaskException() {
        super("No such task :< (Check the index input!!!)");
    }
}
