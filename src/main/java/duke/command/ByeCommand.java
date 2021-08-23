package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Contains the executables when the user uses the 'bye' command.
 */
public class ByeCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showBye();
        storage.save(taskList);
    }

    /**
     * Overrides the isExit function to tell Duke to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
