package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodbye();
        super.isExit = true;
    }
}
