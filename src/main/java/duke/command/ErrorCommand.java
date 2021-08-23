package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
/**
 * Contains the executables when the user inputs an invalid command.
 */
public class ErrorCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showError("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }
}
