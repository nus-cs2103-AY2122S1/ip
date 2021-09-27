package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Encapsulates the Exit command's operations
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.goodbye();
    }
}
