package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnknownCommand extends Command{

    public UnknownCommand() {

    }

    public void execute(TaskList task, Ui ui, Storage storage) {
        ui.showInvalidCommand();
    }

    public boolean isExit() {
        return false;
    }
}
