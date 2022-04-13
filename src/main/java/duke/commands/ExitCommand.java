package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This class encapsulates the exit command.
 */
public class ExitCommand extends Command {
    /**
     * Prints the exit message and closes the scanner.
     *
     * @param tasks The current list of tasks.
     * @param ui The UI of the Duke app.
     * @param storage The storage manager for the Duke app.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
    }

}
