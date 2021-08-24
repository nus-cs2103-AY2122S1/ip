package duke.dukeexception;

import java.io.FileNotFoundException;
import java.io.IOException;

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
