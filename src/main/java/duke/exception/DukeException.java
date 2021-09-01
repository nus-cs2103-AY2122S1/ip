package duke.exception;

/**
 * DukeException is exception when command face error.
 */
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
