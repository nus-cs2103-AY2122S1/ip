/**
 * This exception is thrown when no description for command is inputted.
 */
public class NoDescriptionException extends Exception {

    protected Duke.Command command;

    protected NoDescriptionException(Duke.Command command) {
        super(String.format("No description inputted for %s.", command));
        this.command = command;
    }
}
