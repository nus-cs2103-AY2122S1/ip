package duke.commands;

import duke.utils.TaskList;

/**
 * Encapsulates a command to list all tasks in the task list.
 */
public class ListTasks extends Command {
    @Override
    public TaskList execute (TaskList taskList) {
        return taskList;
    }
}
