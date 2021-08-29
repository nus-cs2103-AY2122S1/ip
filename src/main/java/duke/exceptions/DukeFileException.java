package duke.exceptions;

/**
 * This is a DukeFileException class that extends DukeException.
 * This exception is thrown when Duke gets an error when trying to access or write to a file.
 */
public class DukeFileException extends DukeException {

    /**
     * This is a DukeFileException constructor.
     */
    public DukeFileException() {
        super("I guess I got an error loading data. Hmmm...");
    }

}
