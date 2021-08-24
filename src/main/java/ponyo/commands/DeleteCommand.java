package ponyo.commands;

import ponyo.data.task.TaskList;
import ponyo.ui.Ui;
import ponyo.storage.Storage;

/**
 * Deletes a task identified using its index in the task list.
 */
public class DeleteCommand extends Command {
    private final int taskToDelete;

    public DeleteCommand(int taskToDelete) {
        this.taskToDelete = taskToDelete;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.remove(taskToDelete);
        storage.getFullContents(tasks);
        ui.show("\tNoted. I've removed this task: \n\t\t" +
                tasks.retrieveTask(taskToDelete - 1),
                "\tNow you have " + tasks.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
