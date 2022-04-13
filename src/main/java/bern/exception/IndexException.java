package bern.exception;

/**
 * An exception for accessing index that is not valid.
 */
public class IndexException extends BernException {
    /**
     * Constructor for IndexException.
     *
     * @param text The command that triggered the error.
     */
    public IndexException(String text) {
        super(text + ": This command is trying to mark/delete uncreated event");
    }
}
