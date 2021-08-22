package commands;

import tasks.TaskList;

/**
 * A command to list the task in Duke's taskList.
 */
public class ListCommand implements Command {

    private final TaskList taskList;

    /**
     * Creates a ListCommand to display the task in the taskList.
     *
     * @param taskList The taskList that is to be displayed.
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        this.taskList.listHistory();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void invalidArgumentsProvided() {
        // No arguments are needed for a list command
    }
}
