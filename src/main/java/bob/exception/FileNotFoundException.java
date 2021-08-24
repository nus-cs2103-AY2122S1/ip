package bob.exception;

/**
 * Represents the exception thrown by Bob when it is unable to locate the bob.txt file in the user's computer.
 */
public class FileNotFoundException extends BobException {
    /**
     * Constructor for a new FileNotFoundException instance.
     */
    public FileNotFoundException() {
        super();
    }
}
