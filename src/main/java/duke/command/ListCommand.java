package duke.command;

import duke.task.TaskList;

/**
 * Command to list all tasks in the task list
 */
public class ListCommand extends Command {
    public ListCommand() {
        setCommandString("list");
    }

    /**
     * Lists all tasks
     *
     * @param input Full user input
     * @param taskList The list of tasks
     * @return The response
     */
    @Override
    public String parse(String input, TaskList taskList) {
        return taskList.list();
    }
}
