package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Contains the executables for the 'list' command.
 *
 * @author Benjamin Lui
 */
public class ListCommand extends Command {
    /**
     * Executes the list command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.listView(taskList.getAllTasks());
    }
}
