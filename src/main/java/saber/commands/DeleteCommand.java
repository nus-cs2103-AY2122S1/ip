package saber.commands;

import saber.ui.DeleteUI;
import saber.task.Task;
import saber.TaskList;

/**
 * A class to encapsulate a DeleteCommand
 */
public class DeleteCommand extends SaberCommand {
    private int taskIndex;
    private boolean isBadArgument;

    private DeleteUI deleteUI = new DeleteUI();

    /**
     * A constructor for DeleteCommand
     * @param taskIndex the index of the task to be deleted
     * @param isBadArgument whether the index of the task to be deleted is missing from the command
     *                      or is not an integer
     */
    public DeleteCommand(int taskIndex, boolean isBadArgument) {
        this.taskIndex = taskIndex;
        this.isBadArgument = isBadArgument;
    }

    /**
     * A function to execute the DeleteCommand
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
        Task task = taskList.get(taskIndex);
        taskList.delete(taskIndex);
        int totalTask = taskList.size();
        deleteUI.setSuccessMessage(task, totalTask);
        deleteUI.showSuccess();
    }

    /**
     * A function to determine whether the current command is a terminating command (a ByeCommand)
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
