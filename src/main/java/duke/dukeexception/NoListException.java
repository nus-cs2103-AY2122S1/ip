package duke.dukeexception;


/**
 * Represents an exception when file operations are performed when a file/directory does not exist.
 */
public class NoListException extends DukeException {
    /**
     * Constructs the NoListException from an exception.
     *
     * @param e The given exception.
     */
    public NoListException(Exception e) {
        super(e);
    }
}
