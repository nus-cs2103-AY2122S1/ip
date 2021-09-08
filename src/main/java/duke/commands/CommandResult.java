package duke.commands;

/**
 * Represents the result of a command execution
 */
public class CommandResult {
    public final String result;

    public CommandResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return result;
    }
}
