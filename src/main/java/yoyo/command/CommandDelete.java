package yoyo.command;

import yoyo.core.DialogHandler;
import yoyo.core.Storage;
import yoyo.exception.YoyoException;
import yoyo.task.Task;
import yoyo.task.TaskList;

/**
 * A command subclass representing "delete" command.
 */
public class CommandDelete extends Command {
    /**
     * Constructor for "delete" command class.
     *
     * @param inputTokens Array of string tokens constructed from user input.
     */
    public CommandDelete(String[] inputTokens) {
        super(inputTokens);
    }

    /**
     * Executes "delete" command.
     *
     * @param tasks         Tasks currently in the Yoyo program.
     * @param storage       Storage instance of the Yoyo program.
     * @param dialogHandler Ui instance of Yoyo program.
     * @return The result string to be shown to user.
     * @throws YoyoException
     */
    @Override
    public String execute(TaskList tasks, Storage storage, DialogHandler dialogHandler)
            throws YoyoException {
        checkTwoTokenCommand(this.inputTokens);
        try {
            int taskIndex = Integer.parseInt(inputTokens[1]) - 1;
            Task toRemove = tasks.get(taskIndex);
            tasks.remove(taskIndex);
            return dialogHandler.printRemoveTaskMessage(toRemove, tasks);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            throw new YoyoException.YoyoTaskIndexException("Please enter A valid task index!");
        }
    }

}
