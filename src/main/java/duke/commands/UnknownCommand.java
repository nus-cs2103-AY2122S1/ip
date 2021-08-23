package duke.commands;

import duke.functionality.Storage;
import duke.functionality.Ui;

public class UnknownCommand extends Command {

    public UnknownCommand (){}

    public void execute(Storage storage, Ui ui) {
        ui.unknownCommandMessage();
    }

    public boolean isExit() {
        return false;
    }
}
