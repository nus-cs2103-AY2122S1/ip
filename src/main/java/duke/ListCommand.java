package duke;

/**
 * Encapsulates a command to list out all tasks in a list of tasks.
 */
public class ListCommand implements Command {

    /**
     * Lists out all tasks from task list provided together with its index.
     *
     * @param tasks the current list of tasks.
     * @param ui user interface interacts with the user.
     * @param storage custodian of reading and writing save files.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
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
