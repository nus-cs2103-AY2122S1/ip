package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class HelpCommand implements Command {

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        ui.printHelp();
    }

    @Override
    public boolean isQuit() {
        return false;
    }
}
