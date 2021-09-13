package iris.command;

import iris.exception.NoSuchTaskException;
import iris.storage.Storage;
import iris.task.TaskList;
import iris.ui.Ui;

/**
 * Command to list all tasks in the task list.
 *
 * @author Cheong Yee Ming
 * @version Iris Level-10
 */
public class ListCommand extends Command {

    /**
     * Constructor for a ListCommand.
     *
     * @param taskList Handles all operations regarding tasks.
     * @param storage  Save and load data from local directory.
     * @param ui       Prints message with respect to user input.
     */
    public ListCommand(TaskList taskList, Storage storage, Ui ui) {
        super(taskList, storage, ui);
    }

    /**
     * Executes runCommand.
     * Prints the list of tasks in the task list
     * in order of addition to task list.
     *
     * @return String representation of list of tasks.
     */
    @Override
    public String runCommand() throws NoSuchTaskException {
        return ui.guiListTaskMessage(taskList);
    }
}
