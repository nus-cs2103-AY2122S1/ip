package duke;

/**
 * This class extends InvalidCommandException. It handles cases when users do not
 * give a command that is understood by Duke.
 */
public class UnknownCommandError extends InvalidCommandException {
    /**
     * Constructor for UnknownCommandError
     *
     * @param message This message will be discarded.
     */
    public UnknownCommandError(String message) {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
