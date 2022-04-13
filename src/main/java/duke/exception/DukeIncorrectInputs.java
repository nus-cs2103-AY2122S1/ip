package duke.exception;

/**
 * Represents the Incorrect Inputs Exception class that is thrown
 * when the user enters wrong commands.
 */
public class DukeIncorrectInputs extends DukeException {
    /**
     * Constructor for DukeIncorrectInputs exception object.
     * @param errorMessage string message to be shown
     * @param err error object that the incorrect input is related to.
     */
    public DukeIncorrectInputs(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
