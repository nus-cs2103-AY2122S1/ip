package command;

import data.DukeException;
import data.Storage;
import data.TaskList;
import data.Ui;

public class DoneCommand extends Command{
    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (taskNumber > tasks.getSize() || taskNumber <= 0) {
            throw new DukeException("Please insert a valid task.Task Number!");
        } else {
            tasks.getTask(taskNumber).markAsDone();
            storage.save(tasks);
            ui.showMessage("task.Task marked as done!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
