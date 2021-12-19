package abyss.exception;

/**
 * Represents an exception that occurs when user inputs an invalid task.
 */
public class LoadTaskException extends AbyssException {
    /**
     * Constructs a LoadTaskException.
     *
     * @param message Error message.
     */
    public LoadTaskException(String message) {
        super("Error loading tasks: " + message);
    }
}
