package duke.exception;

/**
 * Thrown when no descriptions are provided for tasks
 */
public class EmptyDescriptionException extends DukeException {
    private final String error;

    /**
     * Constructs EmptyDescriptionException object
     *
     * @param cmd type of cmd with error
     */
    public EmptyDescriptionException(String cmd) {
        super();
        this.error = String.format("OOPS!!! Description should not be empty for %s! Send \"help\" if you need! \n",
            cmd.toUpperCase());
    }

    /**
     * Returns error message
     *
     * @return error message
     */
    @Override
    public String getError() {
        return this.error;
    }

}
