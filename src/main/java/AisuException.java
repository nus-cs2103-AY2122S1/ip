/**
 * Exceptions class for Aisu.
 */
public class AisuException extends Exception {

    public AisuException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Oh no... " + this.getMessage();
    }
}
