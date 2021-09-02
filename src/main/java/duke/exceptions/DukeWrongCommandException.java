package duke.exceptions;

public class DukeWrongCommandException extends DukeException {
    /**
     * Constructor for exception thrown when an alternative
     * command is being suggested
     * @param suggestion alternative command
     */
    public DukeWrongCommandException(String suggestion) {
        super("I do not understand, do you want to use command '"
                + suggestion + "' instead?\n", Type.WRONG_COMMAND);
    }
}
