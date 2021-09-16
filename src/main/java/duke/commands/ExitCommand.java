package duke.commands;

import duke.Storage;
import duke.TaskList;

/**
 * Tells Duke to stop reading inputs.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
    }

    /**
     * Tells the storage to save the most updated list and displays farewell message via the Ui.
     */
    public String execute(TaskList task, Storage storage) {
        storage.save(task);
        return "Bye! Hope to see you again soon!";
    }

    /**
     * Helper function to tell Duke to STOP reading inputs
     */
    public boolean isExit() {
        return true;
    }
}
