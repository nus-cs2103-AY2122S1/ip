package aisu;

/**
 * A dedicated Exception class for Aisu.
 *
 * @author Liaw Xin Yan
 */
public class AisuException extends Exception {

    public AisuException(String message) {
        super(message);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return The Exception message.
     */
    @Override
    public String toString() {
        return "Oh no... " + this.getMessage();
    }
}
