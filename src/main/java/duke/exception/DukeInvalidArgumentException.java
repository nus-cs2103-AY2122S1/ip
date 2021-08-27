package duke.exception;

/**
 * Represents a duke exception when an invalid argument is provided for the command.
 */
public class DukeInvalidArgumentException extends DukeException {

    /**
     * Obtain the string representation of the exception.
     *
     * @return string representation of the exception
     */
    @Override
    public String toString() {
        return String.format("%s You have invalid argument(s) for this command!", super.toString());
    }
}
