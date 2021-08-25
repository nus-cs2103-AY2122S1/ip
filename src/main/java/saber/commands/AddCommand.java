package saber.commands;

import saber.task.Task;
import saber.TaskList;
import saber.ui.AddUI;

public class AddCommand extends SaberCommand {
    private Task task;
    private boolean isMissingDescription;

    private AddUI addUI = new AddUI();

    public AddCommand(String task, boolean isMissingDescription) {
        Task newTask = new Task(task, false);
        this.task = newTask;
        this.isMissingDescription = isMissingDescription;
    }

    public void execute (TaskList taskList) {
        if (isMissingDescription) {
            addUI.showMissingDescriptionError();
            return;
        }
        taskList.add(task);
        int totalTask = taskList.size();
        addUI.setSuccessMessage(task);
        addUI.showSuccess();
    }

    public boolean isExit() {
        return false;
    }
}
