package duke;

/**
 * Encapsulates a command to delete a task from list of tasks.
 */
public class DeleteCommand implements Command {
    private int deleteIndex;

    /**
     * Creates a new command to delete a task from list of tasks.
     *
     * @param deleteIndex the index of the task to be deleted.
     */
    public DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    /**
     * Deletes the task corresponding to the deleteIndex in the task list provided.
     *
     * @param tasks the current list of tasks.
     * @param ui user interface interacts with the user.
     * @param storage custodian of reading and writing save files.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (deleteIndex < 0 || deleteIndex >= tasks.size()) {
            throw new DukeException("Task with that task number does not exist.");
        }
        Task deletedTask = tasks.delete(deleteIndex);
        storage.save(tasks);
        return "Noted. I've removed this task:\n"
                + deletedTask
                + "\nNow you have " + tasks.size() + " tasks in the list.";
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
