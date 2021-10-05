package duke;

/**
 * Class that removes a current tasks from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task from the list and displays it.
     * @param tasks
     * @param u
     * @param storage
     */
    public void execute(TaskList tasks, Ui u, Storage storage) {
        Task task = tasks.deleteTask(this.index);
        u.displayTaskRemoved(task, tasks.size());
    }
}
