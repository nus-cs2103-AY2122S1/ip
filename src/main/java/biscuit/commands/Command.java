package biscuit.commands;

import biscuit.exceptions.BiscuitException;
import biscuit.storage.Storage;
import biscuit.task.TaskList;
import biscuit.ui.Ui;

/**
 * Abstract class for User Commands.
 */
public abstract class Command {

    /** Types of commands available. */
    public enum CommandType {
        ADD,
        DELETE,
        DONE,
        LIST,
        FIND,
        EXIT,
    }

    protected CommandType commandType;
    protected String[] userInputs;

    /**
     * Constructs Command class.
     *
     * @param commandType Type of command.
     * @param userInputs  User input array with this structure: [command, details].
     */
    protected Command(CommandType commandType, String[] userInputs) {
        this.commandType = commandType;
        this.userInputs = userInputs;
    }

    /**
     * Execute command from userInput.
     *
     * @param taskList Task list.
     * @param ui       Ui to display.
     * @param storage  Storage to save to.
     * @throws BiscuitException Invalid input by user.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws BiscuitException;

    /**
     * Checks if command type is EXIT.
     *
     * @return Boolean of is Exit type.
     */
    public boolean isExit() {
        return commandType.equals(CommandType.EXIT);
    }

    /**
     * Gets command type of command.
     *
     * @return Command type.
     */
    public CommandType getCommandType() {
        return commandType;
    }
}
