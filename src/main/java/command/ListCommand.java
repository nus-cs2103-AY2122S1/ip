package command;

import duke.TaskList;

/**
 * Represents the command that lists all the tasks on the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructor for a ListCommand.
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the command of displaying the given task list.
     *
     * @param taskList The task list to be displayed.
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.showList();
    }
}
