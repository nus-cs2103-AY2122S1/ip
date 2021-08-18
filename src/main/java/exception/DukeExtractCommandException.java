package exception;

/**
 * The is the DukeExtractCommandException class that extends from Exception
 * to catch the extract command exception.
 *
 * @author  HU JIAJUN
 * @version %I%, %G%
 * @since   1.0
 */

public class DukeExtractCommandException extends Exception {

    /**
     * This is constructor method of DukeExtractCommandException.
     *
     * @param message exception message
     */
    public DukeExtractCommandException(String message) {
        super(message);
    }
}
