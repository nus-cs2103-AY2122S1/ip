package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Contains the executables when the user inputs an invalid command.
 */
public class ErrorCommand extends Command {
    /**
     * Executes the error command if an invalid command is used.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showError("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }
}
