package duke;

/**
 * Represents the exception to be thrown when invalid user input into Duke is
 * detected.
 */
public class DukeException extends Exception {
    private final String log;

    /**
     * Class constructor which takes in a log which documents the reason for the
     * exception being thrown.
     *
     * @param log Cause of the exception
     */
    public DukeException(String log) {
        this.log = log;
    }

    /**
     * Returns the exception log.
     *
     * @return Exception log
     */
    @Override
    public String toString() {
        return this.log;
    }
}
