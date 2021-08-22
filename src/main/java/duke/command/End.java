package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class End extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.sayBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
