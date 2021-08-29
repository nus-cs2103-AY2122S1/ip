package duke.command;

import duke.task.TaskList;

import duke.util.Ui;
import duke.util.Storage;

/**
 * A class that represents the command when the user types in 'bye'.
 */
public class ByeCommand extends Command {

    /**
     * Prints a goodbye message and exits the program.
     *
     * @param tasks   The current list of tasks from the user.
     * @param ui      An object that handles all UI related functionality. (e.g. printing)
     * @param storage An object that handles all save/load related functionality.
     * @return The input task list, unmodified.
     */
    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Goodbye. Have a nice day!");
        return tasks;
    }

    /**
     * Returns true if the command terminates Duke, false otherwise.
     *
     * @return True, as ByeCommand terminates Duke.
     */
    @Override
    public boolean isTerminated() {
        return true;
    }
}
