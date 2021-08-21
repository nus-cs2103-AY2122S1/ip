package duke;

/**
 * This class handles all exceptions arising within Duke program.
 */
public class InvalidCommandException extends Exception {
    /**
     * Constructor for InvalidCommandException.
     *
     * @param message The message will be formatted as shown below.
     */
    public InvalidCommandException(String message) {
        super(String.format("     ☹ OOPS!!! %s\n", message));
    }
}