package aisu.exception;

/**
 * A dedicated Exception class for Aisu.
 *
 * @author Liaw Xin Yan
 */
public class AisuException extends Exception {

    public AisuException(String message) {
        super(message);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Oh no... " + this.getMessage();
    }
}
