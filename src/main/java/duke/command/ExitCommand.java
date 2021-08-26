package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand implements Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.byeMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ExitCommand)) {
            return false;
        }
        return true;
    }
}
