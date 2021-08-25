package ponyo.commands;

import ponyo.data.task.Task;
import ponyo.data.task.TaskList;
import ponyo.ui.Ui;
import ponyo.storage.Storage;

public class DeleteCommand extends Command {
    private final int taskToDelete;

    public DeleteCommand(int taskToDelete) {
        this.taskToDelete = taskToDelete;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.retrieveTask(taskToDelete - 1);
        tasks.remove(taskToDelete - 1);
        storage.getFullContents(tasks);
        ui.show("\tNoted. I've removed this task: \n\t\t" +
                task,
                "\tNow you have " + tasks.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
