package duke.commands;

import duke.functionality.Storage;
import duke.functionality.Ui;

public class ListCommand extends Command {

    public ListCommand() {}

    public void execute(Storage storage, Ui ui) {
        String printedTaskList = storage.printTaskList();
        ui.printTaskListMessage(printedTaskList);
    }

    public boolean isExit() {
        return false;
    }
}
