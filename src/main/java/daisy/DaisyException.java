package daisy;

/**
 * DaisyException class handles exceptions for Daisy.
 */
public class DaisyException extends Exception {

    public DaisyException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return "Oh no! " + this.getMessage();
    }
}
