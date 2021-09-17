package duke;

public class DukeException extends RuntimeException {
    DukeException(String message) {
        super(message);
    }

    /**
     * Returns the string representation of DukeException.
     *
     * @return The string representation of DukeException.
     */
    @Override
    public String toString() {
        return "DukeException: " + getMessage();
    }
}
