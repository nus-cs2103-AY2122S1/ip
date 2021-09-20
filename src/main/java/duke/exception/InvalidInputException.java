package duke.exception;

/**
 * This is a customised exception stands for user's input does not
 * follow the task's format.
 */
public class InvalidInputException extends DukeException {
    private String operation;

    /**
     * Constructor for creating an InvalidTaskException.
     *
     * @param operation The name of operation user wants Duke to do.
     */
    public InvalidInputException(String operation) {
        this.operation = operation;
    }

    /**
     * Return the warning that the operation user input is invalid.
     * @return The warning that the the operation user input is invalid.
     */
    @Override
    public String getMessage() {
        return "Sorry, your input is invalid, I cannot help you to " + operation;
    }

}