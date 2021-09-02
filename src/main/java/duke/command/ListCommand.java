package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * ListCommand class handles the 'list' command to list out the current tasks.
 */
public class ListCommand extends Command {

    /**
     * Returns the list of tasks in the taskList.
     *
     * @param taskList The TaskList of Duke.
     * @param storage The Storage of Duke.
     * @return Response string.
     * @throws DukeException  If error occur during execution.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return taskList.getTaskList() + taskList.getListStatus();
    }
}
