package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

/**
 * ExitCommand represents a command to exit the programme.
 */
public class ExitCommand extends Command {
    /**
     * Executes the ExitCommand object.
     *
     * @param taskList the current task list
     * @param ui the ui object used
     * @param storage the current storage
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return Ui.showExitMessage();
    }
}
