package duke;

/**
 * Encapsulates a command to add a task to list of tasks.
 */
public class AddCommand implements Command {
    private Task pendingTask;

    /**
     * Creates a new command to add a task to list of tasks.
     *
     * @param newTask the new task to be added.
     */
    public AddCommand(Task newTask) {
        this.pendingTask = newTask;
    }

    /**
     * Adds the task to list of tasks, then display it on the user interface.
     * Afterwards, save the list of tasks to the save file.
     *
     * @param tasks the list of tasks to save the new task into.
     * @param ui user interface that will announce the addition of new task.
     * @param storage object that writes the new list of tasks to save file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(pendingTask);
        storage.save(tasks);
        return "added: " + pendingTask;
    }

    /**
     * Identifies if this command is an exit command.
     *
     * @return false as this is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
