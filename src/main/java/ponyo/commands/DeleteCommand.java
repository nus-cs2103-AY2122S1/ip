package ponyo.commands;

import ponyo.data.task.Task;
import ponyo.data.task.TaskList;
import ponyo.storage.Storage;
import ponyo.ui.Ui;

/**
 * Deletes a task identified using its index in the task list.
 */
public class DeleteCommand extends Command {
    private final int taskToDelete;

    public DeleteCommand(int taskToDelete) {
        this.taskToDelete = taskToDelete;
    }

    @Override
    public String[] execute(TaskList tasks, Storage storage) {
        Task task = tasks.retrieveTask(taskToDelete - 1);
        tasks.remove(taskToDelete - 1);
        storage.getFullContents(tasks);
        return Ui.show("Noted. I've removed this task: \n\t" + task,
                "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
