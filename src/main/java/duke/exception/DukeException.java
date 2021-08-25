package duke.exception;

/**
 * Throw this when the user input is erroneous or does not abide by the rule stipulated.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException with no detailed message.
     */
    public DukeException() {
        super();
    }

    /**
     * Constructs a DukeException with the specified detail message.
     *
     * @param errorMessage Error message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }


}


