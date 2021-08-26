package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;

public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveFile(tasks);
        ui.exit();
    }
}
