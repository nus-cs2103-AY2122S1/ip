package duke;

/**
 * Represents exceptions that belong to a <code>Duke</code>
 * object.
 */
public class DukeException extends Exception {
    private String message;

    /**
     * Constructor.
     *
     * @param message
     */
    public DukeException(String message) {
        super("Ohnoes, " + message + " TwT");
        this.message = "Ohnoes, " + message + " TwT";
    }

    @Override
    public String toString() {
        return this.message;
    }
}
