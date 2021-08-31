package yoyo.command;

import yoyo.core.DialogHandler;
import yoyo.core.Storage;
import yoyo.exception.YoyoException;
import yoyo.task.TaskList;

public class CommandDone extends Command {
    public CommandDone(String[] inputTokens) {
        super(inputTokens);
    }

    /**
     * Executes "done" command.
     *
     * @param tasks Tasks currently in the Yoyo program.
     * @param storage Storage instance of the Yoyo program.
     * @param dialogHandler Ui instance of Yoyo program.
     * @throws YoyoException
     * @return
     */
    @Override
    public String execute(TaskList tasks, Storage storage, DialogHandler dialogHandler)
            throws YoyoException {
        checkCompleteCommand(this.inputTokens);
        try {
            int taskIndex = Integer.parseInt(inputTokens[1]) - 1;
            tasks.get(taskIndex).toggleDone();
            return dialogHandler.printMarkTaskMessage(tasks, taskIndex);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            throw new YoyoException.YoyoTaskIndexException("Please enter A valid task index!");
        }
    }
}
