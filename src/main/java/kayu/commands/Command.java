package kayu.commands;

import kayu.exception.DukeException;
import kayu.service.TaskList;

/**
 * Holds the base logic required for other Command classes to utilise.
 */
public abstract class Command {

    protected final String commandParams;
    private final CommandType commandType;

    /**
     * Initializes the Command instance.
     * 
     * @param commandType {@link kayu.commands.CommandType} for Command instance.
     * @param commandParams String parameters fed into the command by user.
     */
    public Command(CommandType commandType, String commandParams) {
        this.commandType = commandType;
        this.commandParams = commandParams;
    }

    /**
     * Initializes the Command instance.
     * Overloads the {@link #commandParams} as an empty String.
     * 
     * @param commandType {@link kayu.commands.CommandType} for Command instance.
     */
    public Command(CommandType commandType) {
        this.commandType = commandType;
        this.commandParams = "";
    }

    /**
     * Executes the command based on the implemented child instances
     * and returns the outcome as a String.
     * 
     * @param taskList TaskList instance to execute on.
     * @return String feedback of execution/outcome.
     * @throws DukeException If execution of Command fails.
     */
    public abstract String execute(TaskList taskList) throws DukeException;

    /**
     * Returns the of the instance.
     * 
     * @return {@link kayu.commands.CommandType} for Command instance.
     */
    public CommandType getCommandType() {
        return commandType;
    }

    /**
     * Returns the command parameters fed.
     * 
     * @return String parameters fed into the command by user.
     */
    public String getCommandParams() {
        return commandParams;
    }

    /**
     * Checks if the Command instance is a {@link kayu.commands.ByeCommand}
     * using its {@link #commandType}.
     * 
     * @return Boolean true if is {@link kayu.commands.ByeCommand}, else false.
     */
    public boolean isBye() {
        return commandType.equals(CommandType.BYE);
    }
}
