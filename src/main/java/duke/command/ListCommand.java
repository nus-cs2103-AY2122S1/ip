package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    /**
     * Shows the task in list.
     *
     * @param tasks The list of tasks.
     * @param ui The user interaction instance.
     * @param storage The instance to store data.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
