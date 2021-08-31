package janet;

/**
 * Represents the exception to be thrown when invalid user input into Janet is
 * detected.
 */
public class JanetException extends Exception {
    private final String log;

    /**
     * Class constructor which takes in a log which documents the reason for the
     * exception being thrown.
     *
     * @param log Cause of the exception
     */
    public JanetException(String log) {
        this.log = log;
    }

    /**
     * Returns the exception log.
     *
     * @return Exception log
     */
    @Override
    public String toString() {
        return log;
    }
}
