package ponyo.commands;

import ponyo.data.task.TaskList;
import ponyo.storage.Storage;
import ponyo.ui.Ui;

/**
 * Displays the list of stored tasks.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage) {
        for (int i = 0; i < tasks.size(); i++) {
            Ui.show("\t" + (i + 1) + "." + tasks.retrieveTask(i));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
