package duke.util;

/**
 * Exception class for duke.Duke.
 * Used only if the error is not thrown by a standard java exception.
 * This is used in parser to throw exception if commands are not recognised.
 *
 * @author marcuspeh
 * @version A-MoreOOP
 * @since 21 Aug 2021
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
