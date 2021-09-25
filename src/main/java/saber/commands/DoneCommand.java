package saber.commands;

import saber.task.Task;
import saber.tasklist.TaskList;
import saber.ui.DoneUI;

/**
 * Encapsulates a DoneCommand
 */
public class DoneCommand extends SaberCommand {
    private int taskIndex;
    private boolean isBadArgument;

    private DoneUI doneUI = new DoneUI();

    /**
     * Constructs for DoneCommand
     *
     * @param taskIndex the index of the task to be marked as done
     * @param isBadArgument whether the index of the task to be marked as done is missing from the command
     *                      or is not an integer
     */
    public DoneCommand(int taskIndex, boolean isBadArgument) {
        this.taskIndex = taskIndex;
        this.isBadArgument = isBadArgument;
    }

    /**
     * Executes the DoneCommand
     *
     * @param taskList the TaskList from which the specified task is marked as done
     */
    public void execute(TaskList taskList) {
        int totalTask = taskList.size();
        if (isBadArgument) {
            doneUI.showArgumentError();
            return;
        }
        if (taskIndex >= totalTask || taskIndex < 0) {
            doneUI.showUnableToFindTaskError();
            return;
        }
        assert taskIndex < totalTask && taskIndex >= 0 : "Index out of bounds";
        Task task = taskList.get(taskIndex);
        task.markAsDone();
        doneUI.setSuccessMessage(task);
        doneUI.showSuccess();
    }

    /**
     * {@inheritdoc}
     */
    public String getResponse(TaskList taskList) {
        int totalTask = taskList.size();
        if (isBadArgument) {
            return doneUI.getArgumentError();
        }
        if (taskIndex >= totalTask || taskIndex < 0) {
            return doneUI.getUnableToFindTaskError();
        }
        assert taskIndex < totalTask && taskIndex >= 0 : "Index out of bounds";
        Task task = taskList.get(taskIndex);
        task.markAsDone();
        doneUI.setSuccessMessage(task);
        return doneUI.getSuccessMessage();
    }
}
