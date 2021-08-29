package duke;

/**
 * Command to list all Tasks.
 *
 * @author felix-ong
 */
public class ListCommand extends Command {
    private StringBuilder sb;

    public ListCommand() {
        this.sb = new StringBuilder("Here are the tasks in your list:\n");
    }
    /**
     * Executes the specific actions for this command.
     *
     * @param tasks Handles the list of tasks.
     * @param ui Handles the user interface.
     * @param storage Handles the saving and loading of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            sb.append(String.format(" %d. %s%n", i + 1, tasks.getTask(i)));
        }
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
        return sb.toString();
    }
}
