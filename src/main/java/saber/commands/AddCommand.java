package saber.commands;

import saber.task.Task;
import saber.TaskList;
import saber.ui.AddUI;

/**
 * A class to represent an AddCommand
 */
public class AddCommand extends SaberCommand {
    private Task task;
    private boolean isMissingDescription;

    private AddUI addUI = new AddUI();

    /**
     * A constructor for AddCommand
     * @param task the description for the task to be added
     * @param isMissingDescription whether the task description is missing in the command
     */
    public AddCommand(String task, boolean isMissingDescription) {
        Task newTask = new Task(task, false);
        this.task = newTask;
        this.isMissingDescription = isMissingDescription;
    }

    /**
     * A function to execute the AddCommand
     * @param taskList the TaskList to which the newly created task is added to
     */
    public void execute(TaskList taskList) {
        if (isMissingDescription) {
            addUI.showMissingDescriptionError();
            return;
        }
        taskList.add(task);
        int totalTask = taskList.size();
        addUI.setSuccessMessage(task);
        addUI.showSuccess();
    }

    /**
     * A function to determine whether the current command is a terminating command (a ByeCommand)
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
