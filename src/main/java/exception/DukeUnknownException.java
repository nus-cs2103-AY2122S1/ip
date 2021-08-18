package exception;

/**
 * The is the DukeUnknownException class that extends from Exception
 * to catch the unknown exception.
 *
 * @author  HU JIAJUN
 * @version %I%, %G%
 * @since   1.0
 */

public class DukeUnknownException extends Exception {

    /**
     * This is constructor method of DukeUnknownException.
     *
     * @param message exception message
     */
    public DukeUnknownException(String message) {
        super(message);
    }
}
