package duke.exception;

public class DukeFileSystemException extends RuntimeException {
    private static final String preMessage = "ERROR: ";

    /**
     * Constructs a new instance of DukeFileSystemException that handles instances of file read/write errors.
     *
     * @param message the message that corresponds to the exception.
     */
    public DukeFileSystemException(String message) {
        super(preMessage + message);
    }
}
