package duke;

/**
 * Encapsulates a command to complete a task from a list of tasks.
 */
public class DoneCommand implements Command {
    private int doneIndex;

    /**
     * Creates a command to complete a task from a list of tasks.
     *
     * @param doneIndex the index of the task to be completed.
     */
    public DoneCommand(int doneIndex) {
        this.doneIndex = doneIndex;
    }

    /**
     * Completes the task corresponding to the doneIndex in the task list provided.
     *
     * @param tasks the current list of tasks.
     * @param ui user interface interacts with the user.
     * @param storage custodian of reading and writing save files.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (doneIndex < 0 || doneIndex >= tasks.size()) {
            throw new DukeException("Task with that task number does not exist.");
        }
        Task completedTask = tasks.completeTask(doneIndex);
        storage.save(tasks);
        return "Nice! I've marked this task as done:" + completedTask;
    }

    /**
     * Identifies if this command is an exit command.
     *
     * @return whether this command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
