package commands;

/**
 * A command to exit duke.
 */
public class ExitCommand extends Command {

    /**
     * Prints a command to say bye to the user and closed the Chat bot.
     * @return
     */
    @Override
    public CommandReturnStatus execute() {
        this.setExecutionMessage("Bye!! Hope to see you again!!\n");
        return CommandReturnStatus.SUCCESSFUL;
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String getInvalidArgumentsMessage() {
        // Not needed since no arguments are needed for the exit command
        return "";
    }
}
