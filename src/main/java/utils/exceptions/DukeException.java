package utils.exceptions;

public class DukeException extends Exception {
    public static final String ERROR_UNEXPECTED = "Error: Something unexpected happened. Please submit a bug report.";

    public DukeException() {

    }

    public DukeException(String message) {
        super(message);
    }

}
