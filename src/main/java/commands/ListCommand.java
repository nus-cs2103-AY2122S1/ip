package commands;

import tasks.TaskList;

/**
 * A command to list the task in Duke's taskList.
 */
public class ListCommand extends Command {

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
    public boolean execute() {
        this.setExecutionMessage(this.taskList.listHistory());
        return true;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getInvalidArgumentsMessage() {
        // No arguments are needed for a list command
        return "";
    }
}
