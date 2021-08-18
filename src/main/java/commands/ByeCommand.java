package commands;

import core.TaskList;
import gui.Ui;

public class ByeCommand extends Command {
    private Ui ui;

    public ByeCommand() {
        ui = new Ui();
    }

    @Override
    public void execute(TaskList taskList) {
        ui.sayBye();
    }

    @Override
    public boolean shouldExit() {
        return true;
    }
}
