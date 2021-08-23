package duke.commands;

import duke.functionality.Storage;
import duke.functionality.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {}

    public void execute(Storage storage, Ui ui) {
        ui.closeReader();
        storage.saveToFile();
        ui.goodbyeMessage();
    }

    public boolean isExit() {
        return true;
    }

}
