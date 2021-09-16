package daisy.command;

import daisy.task.Storage;
import daisy.task.TaskList;

/**
 * ListCommand class handles the 'list' command to list out the current tasks.
 */
public class ListCommand extends Command {

    /**
     * Returns the list of tasks in the taskList.
     *
     * @param taskList The TaskList of Daisy.
     * @param storage The Storage of Daisy.
     * @return Response string.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return taskList.getTaskList() + taskList.getListStatus();
    }
}
