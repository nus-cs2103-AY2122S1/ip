package duke.response;

/**
 * Represents a response after executing a command.
 */
public class DukeResponse {
    private final String message;
    private final boolean isExit;

    /**
     * Constructor for a <code>DukeResponse</code> object.
     */
    public DukeResponse(String message, boolean isExit) {
        this.message = message;
        this.isExit = isExit;
    }

    /**
     * Constructor for a <code>DukeResponse</code> object that does not exit the application.
     */
    public DukeResponse(String message) {
        this(message, false);
    }

    /**
     * Returns whether the application should exit.
     */
    public boolean isExit() {
        return isExit;
    }

    @Override
    public String toString() {
        return message;
    }
}
