package duke.command;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.UiPane;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Storage storage, UiPane uiPane) {
        uiPane.showTaskList(taskList.getTasks());
        uiPane.showMessage(String.format("You have %d tasks.", taskList.getTaskCount()));
    }
}
