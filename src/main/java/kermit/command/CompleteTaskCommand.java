package kermit.command;

import kermit.KermitException;
import kermit.Ui;
import kermit.ToDo;
import kermit.Storage;
import kermit.tasks.Task;

public class CompleteTaskCommand extends Command {
    // zero indexed
    private int taskNum;

    // taskNum is number in task list, one indexed
    public CompleteTaskCommand(String taskNum) throws KermitException {
        try {
            this.taskNum = Integer.parseInt(taskNum) - 1;
        } catch (NumberFormatException e) {
            throw new KermitException("That is an invalid task number!");
        }

    }

    @Override
    public void execute(ToDo taskList, Ui ui, Storage storage) throws KermitException {
        try {
            Task task = taskList.completeTask(taskNum);
            ui.showCompleteTaskMessage(task);
            storage.save(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new KermitException("That is an invalid task!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}