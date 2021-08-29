package duke;

/**
 * Command to mark a task as done.
 *
 * @author felix-ong
 */
public class DoneCommand extends Command {
    private String index;
    private Task task;

    public DoneCommand(String index) {
        this.index = index;
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
        this.task = tasks.doneTask(storage, this.index);
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

    @Override
    public String toString() {
        return String.format("Good job! I have marked the following task as done:%n %s%n", task);
    }
}
