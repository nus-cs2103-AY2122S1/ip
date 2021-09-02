package edith.commands;

import edith.storage.Storage;
import edith.tasks.TaskList;
import edith.ui.Ui;

/**
 * Lists everything in the taskList.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getSize() > 0) {
            return ui.printList(tasks);
        } else {
            return ui.printEmptyList();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
