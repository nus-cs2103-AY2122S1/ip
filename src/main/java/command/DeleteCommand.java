package command;

import data.DukeException;
import data.Storage;
import data.TaskList;
import data.Ui;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskNumber > tasks.getSize() || taskNumber <= 0) {
            throw new DukeException("Please insert a valid task.Task Number!");
        } else {
            tasks.deleteTask(taskNumber);
            storage.save(tasks);
            ui.showMessage("task.Task deleted!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
