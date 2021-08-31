package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Type of Command that displays all tasks in the task list to the user.
 */
public class ListCommand extends Command {

    /**
     * Constructor.
     */
    public ListCommand() {
    }

    /**
     * Executes a series of operations to generate and display the list of tasks.
     *
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.list(taskList);
    }

    /**
     * Not an Exit Command
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
