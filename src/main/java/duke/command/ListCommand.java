package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * This ListCommand class represents a command to list the current tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Displays the current tasks in the task list to the user.
     *
     * @param tasks The task list.
     * @param storage The storage system of the application.
     * @return Completion message of this command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Here are the tasks in your list:\n" + tasks.toString();
    }
}
