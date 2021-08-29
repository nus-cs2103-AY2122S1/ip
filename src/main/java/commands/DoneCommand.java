package commands;

import tasks.TaskList;

/**
 * A command to marks a task in Duke's taskList as done.
 */
public class DoneCommand extends TaskListIndexCommand {

    private final TaskList taskList;

    /**
     * Creates a DoneCommand to mark a task in the taskList as completed.
     *
     * @param input The input by the user that triggers this command.
     * @param taskList The taskList that contains the task to be mark as done.
     */
    public DoneCommand(String input, TaskList taskList) {
        super(input);
        this.taskList = taskList;
    }

    @Override
    protected String executeOnTaskList(int... listOfIndex) {
        StringBuilder message = new StringBuilder();
        for (int ofIndex : listOfIndex) {
            message.append(this.taskList.markTaskAsCompleted(ofIndex)).append("\n");
        }
        return message.append(this.taskList.getTaskListStatus()).toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getInvalidArgumentsMessage() {
        return "Invalid argument to the \"done\" function.\n";
    }
}
