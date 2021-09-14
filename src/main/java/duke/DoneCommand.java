package duke;

/**
 * Class that updates the status of a task.
 */
public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task as completed and displays it.
     * @param tasks
     * @param u
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui u, Storage storage) {
        tasks.markTaskDone((this.index - 1));
        u.displayTaskDone(tasks.getTask(this.index - 1));
    }
}
