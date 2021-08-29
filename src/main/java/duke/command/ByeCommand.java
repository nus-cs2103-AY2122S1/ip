package duke.command;

import duke.task.*;
import duke.ui.Ui;
import duke.storage.Storage;

public class ByeCommand extends Command{

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
       storage.updateFile(taskList);
        ui.stopMethod();
    }
}
