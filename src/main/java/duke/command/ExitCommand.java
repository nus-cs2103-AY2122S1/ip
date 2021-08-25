package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * ExitCommand class encapsulates command to exit Duke.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodBye();
    }
}
