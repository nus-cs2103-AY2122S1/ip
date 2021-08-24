package ponyo.commands;

import ponyo.data.task.TaskList;
import ponyo.ui.Ui;
import ponyo.storage.Storage;

/**
 * Displays the list of stored tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        for (int i = 1; i <= tasks.size(); i++) {
            ui.show("\t" + i + "." + tasks.retrieveTask(i));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
