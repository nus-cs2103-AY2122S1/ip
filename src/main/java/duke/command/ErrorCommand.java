package duke.command;

import duke.task.*;
import duke.ui.Ui;
import duke.storage.Storage;

public class ErrorCommand extends Command{

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
//        ui.errorCommand();
    }
}
