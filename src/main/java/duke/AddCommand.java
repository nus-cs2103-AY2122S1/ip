package duke;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }
    /**
     * Executes the specific actions for this command.
     *
     * @param tasks Handles the list of tasks.
     * @param ui Handles the user interface.
     * @param storage Handles the saving and loading of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(storage, this.task);
    }

    /**
     * Returns false.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
