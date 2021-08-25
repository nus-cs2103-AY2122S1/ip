package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Tells Duke to stop reading inputs.
 */
public class ExitCommand extends Command{

    public ExitCommand() {
    }

    /**
     * Tells the storage to save the most updated list and displays farewell message via the Ui.
     */
    public void execute(TaskList task, Ui ui, Storage storage) {
        storage.save(task);
        ui.showByeMessage();
    }

    /**
     * Helper function to tell Duke to STOP reading inputs
     */
    public boolean isExit() {
        return true;
    }
}
