package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnknownCommand implements Command {

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        ui.printUnknownCommand();
    }

    @Override
    public boolean isQuit() {
        return false;
    }
}
