package duke.logic.commands;

/**
 * Represents a syntactically invalid command.
 */
public class InvalidCommand extends Command {
    private final String message;

    /**
     * Creates a command to represent the invalid inputs.
     *
     * @param message The error message to be displayed.
     */
    public InvalidCommand(String message) {
        this.message = message;
    }

    /**
     * Returns the error message, as invalid commands are
     * not meaningfully executable.
     *
     * @return The error message to be displayed.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(message);
    }
}
