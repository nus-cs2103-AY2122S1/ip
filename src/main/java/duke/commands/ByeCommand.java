package duke.commands;

import duke.Storage;
import duke.Tasklist;
import duke.Ui;

public class ByeCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) {
        ui.showByeMessage();
    }

}