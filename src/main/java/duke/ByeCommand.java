package duke;

/**
 * Represents a command to exit
 */
public class ByeCommand extends Command {

    /**
     * Executes <code>ByeCommand</code>
     * @param tasks <code>TaskList</code> containing saved tasks
     * @param ui <code>Ui</code> responsible for user interactions
     * @param storage <code>Storage</code> responsible for saving tasks to drive
     * @return corresponding message
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Bye! Hope to see you again soon!";
    }

    /**
     * Returns true if instanceof <code>ByeCommmand</code>
     * @return true
     */
    @Override
    public boolean isBye() {
        return true;
    }
}
