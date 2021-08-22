package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class HelpCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printHelp();
    }
}
