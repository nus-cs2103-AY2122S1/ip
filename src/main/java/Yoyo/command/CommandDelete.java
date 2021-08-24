package Yoyo.command;

import Yoyo.core.Storage;
import Yoyo.core.Ui;
import Yoyo.exception.YoyoException;
import Yoyo.task.Task;
import Yoyo.task.TaskList;

public class CommandDelete extends Command {
    public CommandDelete(String[] inputTokens) {
        super(inputTokens);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui)
            throws YoyoException {
        checkCompleteCommand(this.inputTokens);
        try {
            int taskIndex = Integer.parseInt(inputTokens[1]) - 1;
            Task toRemove = tasks.get(taskIndex);
            tasks.remove(taskIndex);
            ui.printRemoveTaskMessage(toRemove, tasks);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            throw new YoyoException.YoyoTaskIndexException("Please enter A valid task index!");
        }
    }

}
