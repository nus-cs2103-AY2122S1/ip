package jarvis.exception;

/**
 * Encapsulates an exception when the storage file has an invalid task.
 */
public class InvalidStorageTaskException extends JarvisException {
    /**
     * Constructor for InvalidStorageTaskException.
     */
    public InvalidStorageTaskException() {
        super("Invalid task in storage file!");
    }
}
