package calico;

/**
 * Deals with exceptions thrown in the Duke chatbot.
 */
public class CalicoException extends Exception {
    /**
     * Creates a DukeException.
     *
     * @param message Description about the exception.
     */
    public CalicoException(String message) {
        super(message);
    }

    /**
     * Converts DukeException to string format.
     *
     * @return DukeException as a string.
     */
    @Override
    public String toString() {
        return " oops.." + getMessage();
    }
}
