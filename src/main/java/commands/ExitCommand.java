package commands;

import duke.Storage;
import duke.Ui;
import tasktypes.TaskList;

public class ExitCommand extends Command {

    public ExitCommand() {}

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.displayBye();
    }

}
