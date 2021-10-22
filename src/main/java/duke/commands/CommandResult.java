package duke.commands;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {
    private final String result;

    /**
     * Constructs a CommandResult.
     *
     * @param result
     */
    public CommandResult(String result) {
        this.result = result;
    }

    /**
     * Returns a string representation of the CommandResult.
     *
     * @return Result.
     */
    @Override
    public String toString() {
        return result;
    }
}
