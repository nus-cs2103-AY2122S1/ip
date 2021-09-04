package bot.commands;

/**
 * Represents a command to report errors.
 */
public class ErrorCommand extends Command {
    private static final String DEFAULT_ERROR_MESSAGE = "\n\t My dictionary does not contain this sophisticated "
            + "language.\n\t Maybe someday :)";
    private final String errorMessage;

    /**
     * Returns an ErrorCommand with a default error message;
     */
    public ErrorCommand() {
        this.errorMessage = DEFAULT_ERROR_MESSAGE;
    }

    /**
     * Returns an ErrorCommand with a specified error message;
     *
     * @param errorMessage The specified error message;
     */
    public ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Executes the Command and returns a String.
     *
     * @return A String to show to the user after execution of the Command.
     */
    @Override
    public String execute() {
        return errorMessage;
    }
}
