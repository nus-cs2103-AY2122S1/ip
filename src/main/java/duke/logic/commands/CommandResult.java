package duke.logic.commands;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {
    private final String message;

    /**
     * Creates a object to store the result of a command execution.
     *
     * @param message The message obtained after executing the command.
     */
    public CommandResult(String message) {
        this.message = message;
    }

    /**
     * Returns the message description of the execution result.
     *
     * @return The message description of the execution result.
     */
    public String getMessage() {
        return message;
    }
}
