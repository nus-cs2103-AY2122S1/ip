package commands;

/**
 * An abstract class representing commands user has supplied to Duke.
 */
public abstract class Command {

    /**
     * The status of a command after it has been executed.
     * UNSUCCESSFUL: An error occurred while executing the task.
     * CHANGED_TASK_LIST: A command that involves changing the taskList is successful.
     * SUCCESSFUL: A command that does not involve changing the taskList is successful.
     * TASK_MARKED_AS_DONE: The command that marks tasks as done is successful.
     * UNDO_SUCCESSFUL: The command that undo previous changes is successful.
     */
    public enum CommandReturnStatus {
        UNSUCCESSFUL,
        SUCCESSFUL,
        CHANGED_TASK_LIST,
        TASK_MARKED_AS_DONE,
        UNDO_SUCCESSFUL
    }

    /** The text to display to the user after the execution of a command */
    private String executionMessage;

    /**
     * Execute the respective command.
     *
     * @return A command return status based on the enum above.
     */
    public abstract CommandReturnStatus execute();

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
