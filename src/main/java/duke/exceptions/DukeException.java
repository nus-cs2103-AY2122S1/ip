package duke.exceptions;

public class DukeException extends Exception {
    /**
     * Creates exception object for Duke program
     *
     * @param errorMessage The message to be shown to user
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
