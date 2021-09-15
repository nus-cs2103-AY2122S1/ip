package ponyo.commands;

import ponyo.common.Messages;
import ponyo.data.task.TaskList;
import ponyo.storage.Storage;
import ponyo.ui.Ui;

/**
 * Displays the list of stored tasks.
 */
public class ListCommand extends Command {
    @Override
    public String[] execute(TaskList tasks, Storage storage) {
        String[] args = new String[tasks.size()];

        if (tasks.isEmpty()) {
            return Ui.show(Messages.MESSAGE_NO_TASKS);
        }

        for (int i = 0; i < tasks.size(); i++) {
            args[i] = (i + 1) + ". " + tasks.retrieveTask(i);
        }
        return Ui.show(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
