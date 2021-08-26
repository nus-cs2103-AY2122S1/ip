package saber.commands;

import saber.task.Task;
import saber.tasklist.TaskList;
import saber.ui.DoneUI;

/**
 * A class to encapsulate a DoneCommand
 */
public class DoneCommand extends SaberCommand {
    private int taskIndex;
    private boolean isBadArgument;

    private DoneUI doneUI = new DoneUI();

    /**
     * A constructor for DoneCommand
     * @param taskIndex the index of the task to be marked as done
     * @param isBadArgument whether the index of the task to be marked as done is missing from the command
     *                      or is not an integer
     */
    public DoneCommand(int taskIndex, boolean isBadArgument) {
        this.taskIndex = taskIndex;
        this.isBadArgument = isBadArgument;
    }

    /**
     * A function to execute the DoneCommand
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
        Task task = taskList.get(taskIndex);
        task.markAsDone();
        doneUI.setSuccessMessage(task);
        doneUI.showSuccess();
    }

    /**
     * A function to determine whether the current command is a terminating command (a ByeCommand)
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
