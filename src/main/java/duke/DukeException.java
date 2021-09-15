package duke;

public class DukeException extends IllegalArgumentException {

    /**
     * Constructor for class DukeException
     *
     * @param message error message
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns the message of the DukeException
     *
     * @return message of the DukeException
     */
    @Override
    public String toString() {
        return getMessage();
    }
}
