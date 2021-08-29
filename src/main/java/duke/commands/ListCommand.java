package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The ListCommand handles the command to display the tasklist.
 */
public class ListCommand extends Command{

    /**
     * Displays the current TaskList being used.
     *
     * @param taskList The current TaskList being used.
     * @param ui The current Ui being used.
     * @param storage The current Storage being used.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showList(taskList);
    }

}
