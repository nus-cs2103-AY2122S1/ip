package exception;

/**
 * The is the DukeIOException class that extends from Exception
 * to catch the IO exception.
 *
 * @author  HU JIAJUN
 * @version %I%, %G%
 * @since   1.0
 */

public class DukeIOException extends Exception {

    /**
     * This is constructor method of DukeIOException.
     *
     * @param message exception message
     */
    public DukeIOException(String message) {
        super(message);
    }
}
