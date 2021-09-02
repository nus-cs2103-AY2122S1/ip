package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ListCommand class handles the 'list' command to list out the current tasks.
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.getTaskList() + taskList.getListStatus();
    }
}
