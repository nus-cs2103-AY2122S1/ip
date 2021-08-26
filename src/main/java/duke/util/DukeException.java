package duke.util;

/**
 * A class that represents exceptions related to the running of Duke.
 */
public class DukeException extends RuntimeException {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
