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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Bye! Hope to see you again soon!");
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
