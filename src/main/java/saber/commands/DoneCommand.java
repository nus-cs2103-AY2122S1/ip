package saber.commands;

import saber.task.Task;
import saber.TaskList;
import saber.ui.DoneUI;

public class DoneCommand extends SaberCommand {
    private int taskIndex;
    private boolean isBadArgument;

    private DoneUI doneUI = new DoneUI();

    public DoneCommand(int taskIndex, boolean isBadArgument) {
        this.taskIndex = taskIndex;
        this.isBadArgument = isBadArgument;
    }

    public void execute (TaskList taskList) {
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

    public boolean isExit() {
        return false;
    }
}
