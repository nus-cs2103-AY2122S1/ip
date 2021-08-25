package saber.commands;

import saber.TaskList;
import saber.ui.FindUI;

public class FindCommand extends SaberCommand {
    private String findString;
    private boolean isMissingFindString;

    private FindUI findUI = new FindUI();

    public FindCommand(String findString, boolean isMissingFindString) {
        this.findString = findString;
        this.isMissingFindString = isMissingFindString;
    }

    public void execute (TaskList taskList) {
        if (isMissingFindString) {
            findUI.showMissingFindStringError();
            return;
        }
        findUI.setSuccessMessage(findString, taskList);
        findUI.showSuccess();
    }

    public boolean isExit() {
        return false;
    }
}
