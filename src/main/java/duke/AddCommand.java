package duke;

public class AddCommand extends Command {
    private Task task;

    /**
     * Initializes the inputted task that is supposed to be added.
     * @param task
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the tasks to the current list of tasks.
     * @param tasks
     * @param u
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui u, Storage storage) {
        tasks.addTask(task);
        u.displayTaskAdded(task, tasks.size() - 1);
    }
}
