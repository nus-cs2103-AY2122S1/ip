package katheryne;

/**
 * An exception to be thrown when the command is not one of those which Katheryne recognises.
 */
public class UnknownCommandException extends KatheryneException {
    public UnknownCommandException() {
        super("I'm... not sure what to say to that... ");
    }
}
