package duke.exception;

/**
 * Thrown when user provides additional inputs or descriptions for commands which do not need them.
 */
public class ExtraInputException extends DukeException {
    private final String error;

    /**
     * Constructs ExtraInputException object
     *
     * @param cmd type of cmd with error
     */
    public ExtraInputException(String cmd) {
        super();
        this.error = String.format("OOPS!!! Description should be empty for %s! Send \"help\" if you need! \n",
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
