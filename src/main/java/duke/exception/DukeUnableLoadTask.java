package duke.exception;

/**
 * Represents the DukeUnableLoadTask Exception class
 * that is thrown when the saved file is corrupted and
 * unable to be loaded.
 */
public class DukeUnableLoadTask extends DukeException {
    /**
     * Constructor for the DukeUnableLoadTask exception.
     */
    public DukeUnableLoadTask() {
        super("Duke was unable to load your file", new Throwable());
    }
}
