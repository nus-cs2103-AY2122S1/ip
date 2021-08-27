package duke.commands;

import duke.data.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
