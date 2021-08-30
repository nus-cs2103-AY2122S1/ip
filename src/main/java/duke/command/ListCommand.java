package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to list all tasks in the task list.
 *
 * @author Cheong Yee Ming
 * @version Duke A-JavaDoc
 */
public class ListCommand extends Command {
    /**
     * Constructor for a DukeCommand.
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
     */
    @Override
    public void runCommand() {
        ui.listTaskMessage(taskList);
    }
}
