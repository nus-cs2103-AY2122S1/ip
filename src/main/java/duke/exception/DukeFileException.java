package duke.exception;

/**
 * Represents file related exceptions during Duke operations.
 */
public class DukeFileException extends Exception {
    /**
     * Creates a new DukeFileException.
     *
     * @param msg message to be added.
     */
    public DukeFileException(String msg) {
        super(msg);
    }

    /**
     * Creates a new DukeFileException.
     * Customizes the error message with additional information.
     *
     * @param customMsg Custom message by the user.
     * @param additionalMsg Additional message.
     */
    public DukeFileException(String customMsg, String additionalMsg) {
        super(String.format("%s\n%s", customMsg, additionalMsg));
    }
}
