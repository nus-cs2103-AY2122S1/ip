package saber.commands;

import saber.task.Task;
import saber.tasklist.TaskList;
import saber.ui.DeleteUI;

/**
 * Encapsulates a DeleteCommand
 */
public class DeleteCommand extends SaberCommand {
    private int taskIndex;
    private boolean isBadArgument;

    private DeleteUI deleteUI = new DeleteUI();

    /**
     * Constructs for DeleteCommand
     *
     * @param taskIndex the index of the task to be deleted
     * @param isBadArgument whether the index of the task to be deleted is missing from the command
     *                      or is not an integer
     */
    public DeleteCommand(int taskIndex, boolean isBadArgument) {
        this.taskIndex = taskIndex;
        this.isBadArgument = isBadArgument;
    }

    /**
     * Executes the DeleteCommand
     *
     * @param taskList the TaskList from which the specified task is deleted
     */
    public void execute(TaskList taskList) {
        if (isBadArgument) {
            deleteUI.showArgumentError();
            return;
        }
        if (taskIndex >= taskList.size() || taskIndex < 0) {
            deleteUI.showUnableToFindTaskError();
            return;
        }
        assert taskIndex < taskList.size() && taskIndex >= 0 : "Index out of bounds";
        Task task = taskList.get(taskIndex);
        taskList.delete(taskIndex);
        int totalTask = taskList.size();
        deleteUI.setSuccessMessage(task, totalTask);
        deleteUI.showSuccess();
    }

    /**
     * {@inheritdoc}
     */
    public String getResponse(TaskList taskList) {
        if (isBadArgument) {
            return deleteUI.getArgumentError();
        }
        if (taskIndex >= taskList.size() || taskIndex < 0) {
            return deleteUI.getUnableToFindTaskError();
        }
        assert taskIndex < taskList.size() && taskIndex >= 0 : "Index out of bounds";
        Task task = taskList.get(taskIndex);
        taskList.delete(taskIndex);
        int totalTask = taskList.size();
        deleteUI.setSuccessMessage(task, totalTask);
        return deleteUI.getSuccessMessage();
    }
}
