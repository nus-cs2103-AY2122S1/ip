package duke.exception;

/**
 * Exception when users type invalid input.
 */
public class DukeExcpetion extends Exception {
    public DukeExcpetion(String message) {
        super(message);
    }

    public String toString() {
        return "OOPS!!! " + this.getMessage();
    }
}