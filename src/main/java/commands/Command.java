package commands;

/**
 * An abstract class representing commands user has supplied to Duke.
 */
public abstract class Command {

    /** The text to display to the user after the execution of a command */
    private String executionMessage;

    /**
     * Execute the respective command.
     *
     * @return True if the command is executed successfully, else false.
     */
    public abstract boolean execute();

    /**
     * Returns true if the command is supposed to exit the chat.
     *
     * @return True if the command should exit the chat.
     */
    public abstract boolean isExit();

    /**
     * Returns a message to the user when the user supplied an invalid argument
     * to a command. Not all commands require this since some commands has no arguments
     * required and thus cannot have an invalid argument.
     *
     * @return The invalid message that should be shown to the user.
     */
    protected abstract String getInvalidArgumentsMessage();

    protected void setExecutionMessage(String executionMessage) {
        this.executionMessage = executionMessage;
    }

    public String getExecutionMessage() {
        return this.executionMessage;
    }
}
