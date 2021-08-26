package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.gui.Ui;

/**
 * Encapsulates a command to terminate Duke.
 */
public class ByeCommand extends Command {
    public ByeCommand() {
        // Do nothing as there are no attributes to initialize
    }

    /**
     * Prints out a farewell message
     *
     * @param taskList The TaskList object storing all the tasks.
     * @param storage The Storage object which was instantiated with the appropriate storage filepath.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        Ui.sayBye();
    }

    /**
     * Returns a boolean specifying whether Duke should terminate.
     *
     * @return true
     */
    @Override
    public boolean shouldExit() {
        return true;
    }
}
