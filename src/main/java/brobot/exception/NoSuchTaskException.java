package brobot.exception;

/**
 * Represents an exception caused when no such task is found for a command that needs a task number
 */
public class NoSuchTaskException extends BroException {
    /**
     * Constructor for the exception.
     */
    public NoSuchTaskException() {
        super("Ey bro, no such task lah!");
    }
}
