package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Contains the executables when the user uses the 'bye' command.
 */
public class ByeCommand extends Command {
    /**
     * Executes the bye command.
     */
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
