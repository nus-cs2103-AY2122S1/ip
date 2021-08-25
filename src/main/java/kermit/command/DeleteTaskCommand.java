package kermit.command;

import kermit.KermitException;
import kermit.Ui;
import kermit.ToDo;
import kermit.Storage;
import kermit.tasks.Task;

public class DeleteTaskCommand extends Command {
    // zero-indexed
    int taskNum;

    // taskNum is number in task list, one indexed
    public DeleteTaskCommand(String taskNum) throws KermitException {
        try {
            this.taskNum = Integer.parseInt(taskNum) - 1;
        } catch (NumberFormatException e) {
            throw new KermitException("That is an invalid task number!");
        }

    }

    @Override
    void execute(ToDo taskList, Ui ui, Storage storage) throws KermitException {
        try {
            Task deletedTask = taskList.deleteTask(taskNum);
            ui.showDeleteTaskMessage(deletedTask, taskList);
            storage.save(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new KermitException("That is an invalid task!");
        }
    }

    @Override
    boolean isExit() {
        return false;
    }
}