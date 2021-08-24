package kayu.commands;

import kayu.exception.DukeException;
import kayu.service.TaskList;

/**
 * Command class.
 * 
 * This class holds the base logic required for other Command classes to utilise.
 */
public abstract class Command {
    
    private final CommandType commandType;
    
    protected final String commandParams;

    public Command(CommandType commandType, String commandParams) {
        this.commandType = commandType;
        this.commandParams = commandParams;
    }

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
    
    public CommandType getCommandType() {
        return commandType;
    }

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
