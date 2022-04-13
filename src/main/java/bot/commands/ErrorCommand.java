package bot.commands;

import static bot.constants.GlobalStringFormats.LINE_BREAK;

/**
 * Represents a command to report errors.
 */
public class ErrorCommand extends Command {
    private static final String DEFAULT_ERROR_MESSAGE = LINE_BREAK + "What is this abomination?!? This language is too "
            + "sophisticated! Please refer to the \"help\" instruction for something I can do.";
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
     * @return A String to show to the user after execution of the ErrorCommand.
     */
    @Override
    public String execute() {
        return errorMessage;
    }
}
