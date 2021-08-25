package saber.commands;

import saber.TaskList;
import saber.ui.ListUI;

public class ListCommand extends SaberCommand {
    private ListUI listUI;

    public void execute (TaskList taskList) {
        listUI = new ListUI(taskList);
        listUI.showSuccess();
    }

    public boolean isExit() {
        return false;
    }
}
