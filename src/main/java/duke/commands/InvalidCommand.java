package duke.commands;

/**
 * Constructs an Invalid Command.
 */
public class InvalidCommand extends Command {

    private String errorMessage;

    /**
     * Constructs an Invalid Command.
     * @param s Invalid command message.
     */
    public InvalidCommand(String s) {
        errorMessage = s;
    }

    /**
     * Executes the task and returns a CommandResult.
     *
     * @return CommandResult.
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(this.errorMessage);
    }
}
