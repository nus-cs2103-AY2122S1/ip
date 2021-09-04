package duke;

/**
 * Represents a command. <code>Command</code> is abstract and contains an <code>execute</code> and <code>isBye</code>
 * method
 */
public abstract class Command {
    /**
     * Executes the corresponding <code>Command</code>
     * @param tasks <code>TaskList</code> containing saved tasks
     * @param ui <code>Ui</code> responsible for user interactions
     * @param storage <code>Storage</code> responsible for saving tasks to drive
     * @return corresponding message
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns true if <code>Command</code> is <code>ByeCommand</code> false otherwise
     * @return boolean if <code>Command</code> is <code>ByeCommand</code>
     */
    public boolean isBye() {
        return false;
    }
}
