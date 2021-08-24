package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
/**
 * Contains the executables for the 'list' command.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.listView(taskList.getAllTasks());
    }
}
