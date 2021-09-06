package duke.command;

import duke.task.TaskList;

/**
 * Command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    public ListCommand() {
        setCommandString("list");
    }

    /**
     * Lists all tasks.
     *
     * @param input Full user input.
     * @param taskList The list of tasks.
     * @return The response that lists all tasks.
     */
    @Override
    public String parse(String input, TaskList taskList) {
        assert input.substring(0, getCommandLength() - 1).equals(getCommandString())
                : "Input should start with command";
        assert taskList != null : "taskList should not be null";

        return taskList.list();
    }
}
