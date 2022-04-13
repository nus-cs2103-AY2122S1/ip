package yoyo.command;

import yoyo.core.DialogHandler;
import yoyo.core.Storage;
import yoyo.exception.YoyoException;
import yoyo.task.Task;
import yoyo.task.TaskList;

/**
 * A command subclass representing "done" command.
 */
public class CommandDone extends Command {
    /**
     * Constructor for "done" command class.
     *
     * @param inputTokens Array of string tokens constructed from user input.
     */
    public CommandDone(String[] inputTokens) {
        super(inputTokens);
    }

    /**
     * Executes "done" command.
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
            Task taskToMarkDone = tasks.get(taskIndex);
            taskToMarkDone.toggleDone();
            return dialogHandler.printMarkTaskMessage(taskToMarkDone);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            throw new YoyoException.YoyoTaskIndexException("Please enter A valid task index!");
        }
    }
}
