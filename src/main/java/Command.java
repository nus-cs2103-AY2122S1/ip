/**
 * Abstract class for User Commands
 */
public abstract class Command {

    protected CommandType commandType;
    protected String[] userInput;
    /**
     * Constructor for Command class
     *
     * @param commandType Type of command
     * @param userInput   User input array with this structure: [command, details]
     */
    protected Command(CommandType commandType, String[] userInput) {
        this.commandType = commandType;
        this.userInput = userInput;
    }

    /**
     * Execute command from userInput
     *
     * @param taskList Task list
     * @param ui       Ui to display
     * @param storage  Storage to save to
     * @throws BiscuitException Invalid input by user
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws BiscuitException;

    /**
     * Checks if command type is EXIT
     *
     * @return Boolean of is Exit type
     */
    public boolean isExit() {
        return this.commandType.equals(CommandType.EXIT);
    }

    /**
     * Types of commands available
     */
    public enum CommandType {
        ADD,
        DELETE,
        DONE,
        LIST,
        EXIT,
    }

}
