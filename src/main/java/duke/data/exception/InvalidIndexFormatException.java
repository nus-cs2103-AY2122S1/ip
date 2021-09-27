package duke.data.exception;

/**
 * Describes an exception caused by invalid formatting when index is required
 */
public class InvalidIndexFormatException extends DukeException {
    @Override
    public String toString() {
        return "OOPS!!! Please enter a number to follow that command (eg. 1, 2) :(";
    }
}
