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
     * Returns a farewell message
     *
     * @param taskList The TaskList object storing all the tasks.
     * @param storage The Storage object which was instantiated with the appropriate storage filepath.
     * @return The farewell message.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return Ui.farewellMessage();
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
