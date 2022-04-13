package duke.exceptions;

public class DucWrongCommandException extends DucException {
    /**
     * Constructor for exception thrown when an alternative
     * command is being suggested
     * @param suggestion alternative command
     */
    public DucWrongCommandException(String suggestion) {
        super("Do you want to use '" + suggestion + "' instead? Cuz I cannot understand this\n");
    }
}
