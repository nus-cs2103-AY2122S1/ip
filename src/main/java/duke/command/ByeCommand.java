package duke.command;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.UiPane;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList taskList, Storage storage, UiPane uiPane) {
        uiPane.close();
    }
}
