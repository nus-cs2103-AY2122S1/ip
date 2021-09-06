package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Contains the executables when the user inputs an invalid command.
 *
 * @author Benjamin Lui
 */
public class ErrorCommand extends Command {
    private final String errorMessage = "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    /**
     * Executes the error command if an invalid command is used.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showError(errorMessage);
    }
}
