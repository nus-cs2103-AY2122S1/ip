package duke.exception;

public class DukeException extends Exception {

    /**
     * Constructs DukeException object.
     *
     * @param errorMessage Error message to be shown to user.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
