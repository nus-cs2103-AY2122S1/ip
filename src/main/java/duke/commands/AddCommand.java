package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

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
