package duke.command;

import duke.result.Result;
import duke.result.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * A class that represents the command when the user types in 'bye'.
 */
public class ByeCommand extends Command {

    /**
     * Prints a goodbye message and exits the program.
     *
     * @param taskList   The current list of tasks from the user.
     * @param ui      An object that handles all UI related functionality. (e.g. printing)
     * @param storage An object that handles all save/load related functionality.
     * @return A Result object containing task and alias data, as well as an output message.
     */
    @Override
    public Result execute(TaskList taskList, Ui ui, Storage storage) {
        return new Result(taskList, ui.showMessage("Goodbye. Have a nice day!"));
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
