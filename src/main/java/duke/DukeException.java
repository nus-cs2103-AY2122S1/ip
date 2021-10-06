package duke;

/**
 * This class represents a {@code DukeException}. This custom exception extends
 * {@code RuntimeException} is thrown whenever {@code Duke} runs into a known
 * error or exception.
 */
public class DukeException extends RuntimeException {
    /**
     * Constructs a new {@code DukeException} with the given error message.
     *
     * @param errMessage Error message to be displayed.
     */
    public DukeException(String errMessage) {
        super(errMessage);
    }
}
