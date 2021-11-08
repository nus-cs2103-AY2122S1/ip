package duke.exception;

/**
 * DukeException class encapsulates the exceptions being thrown by the Duke bot.
 */
public class DukeException extends RuntimeException {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Returns the error message of the exception
     *
     * @return respective error message
     */
    @Override
    public String toString() {
        return "\uD83D\uDE41" + " OOPS!!! " + getMessage();
    }
}
