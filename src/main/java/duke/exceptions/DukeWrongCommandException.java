package duke.exceptions;

public class DukeWrongCommandException extends DukeException {
    /**
     * Constructor for exception thrown when an alternative
     * command is being suggested
     * @param suggestion alternative command
     */
    public DukeWrongCommandException(String suggestion) {
        super("Do you want to use '" + suggestion + "' instead? Cuz I cannot understand this\n");
    }
}
