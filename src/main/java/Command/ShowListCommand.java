package Command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command executed when user asks to see the list of tasks.
 *
 * @author Quan Teng Foong
 */
public class ShowListCommand extends Command {

    /**
     * Prints the TaskList.
     *
     * @param taskList the current list of tasks
     * @param ui the user interface object
     * @param storage the storage object
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showList(taskList);
    }
}
