package saber.commands;

import saber.task.Task;
import saber.tasklist.TaskList;
import saber.ui.AddUI;

/**
 * Represents an AddCommand
 */
public class AddCommand extends SaberCommand {
    private Task task;
    private boolean isMissingDescription;

    private AddUI addUI = new AddUI();

    /**
     * Constructs for AddCommand
     *
     * @param task the description for the task to be added
     * @param isMissingDescription whether the task description is missing in the command
     */
    public AddCommand(String task, boolean isMissingDescription) {
        Task newTask = new Task(task, false);
        this.task = newTask;
        this.isMissingDescription = isMissingDescription;
    }

    /**
     * Executes the AddCommand
     *
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
     * {@inheritdoc}
     */
    public String getResponse(TaskList taskList) {
        if (isMissingDescription) {
            return addUI.getMissingDescriptionError();
        }
        taskList.add(task);
        int totalTask = taskList.size();
        addUI.setSuccessMessage(task);
        return addUI.getSuccessMessage();
    }
}
