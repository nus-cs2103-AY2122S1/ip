package duke;

/**
 * Exception class for all Duke-related issues.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! " + this.getMessage();
    }
}
