package commands;

import tasks.TaskList;
import tasks.TaskListHistory;

import java.util.ArrayList;

/**
 * A command to marks a task in Duke's taskList as done.
 */
public class DoneCommand extends TaskListIndexCommand {

    private final TaskList taskList;
    private final TaskListHistory taskListHistory;

    /**
     * Creates a DoneCommand to mark a task in the taskList as completed.
     *
     * @param input The input by the user that triggers this command.
     * @param taskList The taskList that contains the task to be mark as done.
     */
    public DoneCommand(String input, TaskList taskList, TaskListHistory history) {
        super(input);
        this.taskList = taskList;
        this.taskListHistory = history;
    }

    @Override
    protected CommandReturnStatus executeOnTaskList(int... listOfIndex) {
        StringBuilder message = new StringBuilder();
        ArrayList<Integer> stateChanges = new ArrayList<>();

        for (int index : listOfIndex) {
            message.append(this.taskList.markTaskAsDone(index)).append("\n");
            stateChanges.add(index);
        }

        this.taskListHistory.addTaskStateChanges(stateChanges);
        this.setExecutionMessage(message.append(this.taskList.getTaskListStatus()).toString());
        return CommandReturnStatus.TASK_MARKED_AS_DONE;
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
