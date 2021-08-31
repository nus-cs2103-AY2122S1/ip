package sora.exception;

/**
 * Thrown when user inputs an unknown / invalid command.
 *
 * @author Zhang Shi Chen
 */
public class UnknownCommandException extends SoraException {
    /**
     * Thrown when user inputs an unknown / invalid command.
     * Redirects them to help function.
     */
    public UnknownCommandException() {
        super("Sorry but my database does not have such command.\n"
            + "Try typing 'help' for more information regarding this app!");
    }
}
