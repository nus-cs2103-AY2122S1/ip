package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ClearCommand extends Command {

    /**
     * Displays the current TaskList being used.
     *
     * @param taskList The current TaskList being used.
     * @param ui The current Ui being used.
     * @param storage The current Storage being used.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.clearTasks();
        storage.write(taskList);
        return ui.showTasklistClear();
    }
}
