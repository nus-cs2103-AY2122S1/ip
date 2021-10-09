package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {
    /**
     * Shows exit message.
     *
     * @param tasks The list of tasks.
     * @param ui The user interaction instance.
     * @param storage The instance to store data.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
