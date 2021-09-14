package duke.commands;

import duke.tasks.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * The command indicating that the user wants to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int taskNum;

    /**
     * Constructor for DeleteCommand.
     *
     * @param taskNum the task number of the task that you want to delete.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Performs the actions that adds the deletes the task from the task list.
     *
     * @param tasks the full task list containing all the tasks.
     * @param ui the ui instance.
     * @param storage the storage instance.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskToDelete = tasks.getTask(this.taskNum);
        assert (taskToDelete != null) : "A task you want to delete cannot be null.";

        tasks.deleteTask(this.taskNum);
        storage.removePersistedTask(this.taskNum);
        ui.showTaskDeletedInteraction(taskToDelete, tasks);
    }
}
