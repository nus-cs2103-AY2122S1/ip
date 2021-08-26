package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ListCommand class handles the 'list' command to list out the current tasks.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String message = taskList.getTaskList() + taskList.getListStatus();
        ui.printMessage(message);
    }
}
