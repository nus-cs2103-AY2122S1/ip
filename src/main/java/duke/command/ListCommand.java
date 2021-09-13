package duke.command;

import duke.Storage;
import duke.task.TaskList;

/**
 * Represents the command when the user wants to list the tasks in the task list.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    /**
     * Executes the command to list the tasks in the task list.
     *
     * @param tasks The user's task list.
     * @param storage Storage object used to save the task list to the data file.
     * @return A message listing out all the tasks in the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.listTasks();
    }
}
