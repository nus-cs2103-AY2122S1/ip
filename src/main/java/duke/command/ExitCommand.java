package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This ExitCommand class represents a command to exit the system.
 */
public class ExitCommand extends Command {

    /**
     * Exits the system and displays a farewell message to the user.
     *
     * @param tasks The task list.
     * @param ui The UI of the application.
     * @param storage The storage system of the application.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
    }

    /**
     * Indicates that this command intends to exit the system.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
