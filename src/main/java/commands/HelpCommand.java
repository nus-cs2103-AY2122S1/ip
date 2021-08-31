package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class HelpCommand implements Command {

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        return ui.getHelpMessage();
    }

    @Override
    public boolean isQuit() {
        return false;
    }
}
