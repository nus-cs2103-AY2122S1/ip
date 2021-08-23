package commands;

import duke.Storage;
import duke.Ui;
import tasktypes.TaskList;

public class WrongCommand extends Command {

    public WrongCommand() {}

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.displayWrongCommand();
    }
}
