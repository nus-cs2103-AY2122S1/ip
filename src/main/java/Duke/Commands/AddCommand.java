package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Task.Task;
import Duke.Task.TaskList;
import Duke.Ui.Ui;

public class AddCommand extends Command {

    public boolean isExit = false;

    private Task taskToAdd;

    public AddCommand(Task task) {
        super(CommandType.ADD, false);
        this.taskToAdd = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.addToStorage(taskToAdd);
        tasks.addToTaskList(taskToAdd);
        ui.showAddTaskMessage(taskToAdd, tasks);
    }
}
