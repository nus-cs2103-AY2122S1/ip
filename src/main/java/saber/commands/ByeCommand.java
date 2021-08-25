package saber.commands;

import saber.TaskList;
import saber.ui.ByeUI;

public class ByeCommand extends SaberCommand {
    private ByeUI byeUI = new ByeUI();

    public void execute (TaskList taskList) {
        byeUI.showSuccess();
    }

    public boolean isExit() {
        return true;
    }
}
