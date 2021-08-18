package commands;

import core.TaskList;
import gui.Ui;

public class ByeCommand extends Command {

    public ByeCommand() {}

    @Override
    public void execute(TaskList taskList) {
        Ui.sayBye();
    }

    @Override
    public boolean shouldExit() {
        return true;
    }
}
