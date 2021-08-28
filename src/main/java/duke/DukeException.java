package duke;

/**
 * A representation of exceptions that belong to a <code>Duke</code>
 * object.
 */
public class DukeException extends Exception {
    private String message;
    public DukeException(String message) {
        super("Oops, " + message);
        this.message = "Oops, " + message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
