package duke.exception;

public class DukeException extends Exception {
    /** The error message of this exception */
    private String message;

    public DukeException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
