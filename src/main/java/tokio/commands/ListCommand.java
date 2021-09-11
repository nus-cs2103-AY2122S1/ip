package tokio.commands;

import tokio.storage.Storage;
import tokio.tasks.TaskList;
import tokio.ui.Ui;

/**
 * Lists everything in the taskList.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getSize() == 0) {
            return ui.printEmptyList();
        }
        return ui.printList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
