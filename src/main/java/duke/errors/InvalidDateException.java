package duke.errors;

public class InvalidDateException extends DukeException {

    /**
     * Constructor for duke.DukeException class.
     *
     * @param errorMessage to print to screen.
     */
    public InvalidDateException(String errorMessage) {
        super("Oops! Date input is incorrect. " + errorMessage);

    }
}
