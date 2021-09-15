package ponyo.commands;

import ponyo.common.Messages;
import ponyo.data.task.Task;
import ponyo.data.task.TaskList;
import ponyo.storage.Storage;
import ponyo.ui.Ui;

/**
 * Deletes a task identified using its index in the task list.
 */
public class DeleteCommand extends Command {
    private final int taskIdToDelete;

    public DeleteCommand(int taskIdToDelete) {
        this.taskIdToDelete = taskIdToDelete;
    }

    @Override
    public String[] execute(TaskList tasks, Storage storage) {
        assert taskIdToDelete > 0 && taskIdToDelete <= tasks.size();
        if (taskIdToDelete > tasks.size()) {
            return Ui.show(Messages.MESSAGE_INVALID_INDEX);
        }
        Task task = tasks.retrieveTask(taskIdToDelete - 1);
        tasks.remove(taskIdToDelete - 1);
        storage.getFullContents(tasks);
        return Ui.show("Noted. I've removed this task: \n\t" + task,
                "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
