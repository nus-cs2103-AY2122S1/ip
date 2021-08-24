package command;

import data.Storage;
import data.TaskList;
import data.Ui;
import task.Task;

public class AddCommand extends Command {
    private Task task;
    //For deadline todo and event tasks
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.save(tasks);
        ui.showMessage("task.Task added successfully!");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
