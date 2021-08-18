package exception;

/**
 * The is the DukeTaskNumberOutOfBoundsException class that extends from Exception
 * to catch the task number out of bounds exception.
 *
 * @author  HU JIAJUN
 * @version %I%, %G%
 * @since   1.0
 */

public class DukeTaskNumberOutOfBoundsException extends Exception {

    /**
     * This is constructor method of DukeTaskNumberOutOfBoundsException.
     *
     * @param message exception message
     */
    public DukeTaskNumberOutOfBoundsException(String message) {
        super(message);
    }
}
