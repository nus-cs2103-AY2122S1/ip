package duke.command;

import duke.task.TaskList;
import duke.util.Storage;

/**
 * Lists all the task in the task list.
 */
public class ListCommand extends Command {
    /**
     * Retrieves all task in task list, attach task numbers and print to screen.
     *
     * @param storage storage instance initialised when duke is created.
     * @param taskList task list instance initialised when duke is created.
     * @return String message to indicate if task listed out successfully.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        return String.format("Can do! Here are your %d tasks\n%s",
                    taskList.getSize(),
                    taskList.list());
    }
}
