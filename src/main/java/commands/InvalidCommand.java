package commands;

/**
 * A command that represents an invalid command given by the user. This command
 * occurs when the user enter an input that Duke does not recognise.
 */
public class InvalidCommand extends Command {

    /**
     * Displays a message to the user that he has entered an invalid input to Duke.
     *
     * @return The command return status.
     */
    @Override
    public CommandReturnStatus execute() {
        this.setExecutionMessage("Invalid input. Please try again.\n");
        return CommandReturnStatus.SUCCESSFUL;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getInvalidArgumentsMessage() {
        return "";
        // Not needed
    }
}
