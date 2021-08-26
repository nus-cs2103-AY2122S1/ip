package duke;

public class DukeException extends RuntimeException {
    /**
     * A constructor for the DukeException.
     *
     * @param errorMessage error message output
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
