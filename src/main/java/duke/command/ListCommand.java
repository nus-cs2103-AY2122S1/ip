package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.showListMessage(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
