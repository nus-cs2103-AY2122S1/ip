package bob.exception;

/**
 * Represents the exception thrown by Bob when it is unable to locate the data directory in the user's computer.
 */
public class DirectoryNotFoundException extends BobException {
    /**
     * Constructor for a new DirectoryNotFoundException instance.
     */
    public DirectoryNotFoundException() {
        super();
    }
}
