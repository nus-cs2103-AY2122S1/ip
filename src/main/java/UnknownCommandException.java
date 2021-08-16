/**
 * This exception is thrown when the command is not recognised by Duke.
 */
public class UnknownCommandException extends Exception {
    protected UnknownCommandException(String command) {
        super(String.format("Unrecognised command \"%s\" is inputted.", command));
    }
}
