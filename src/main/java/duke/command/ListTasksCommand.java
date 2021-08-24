package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListTasksCommand extends Command {
    @Override
    public String runAndGenerateDescription(TaskList taskList, Ui ui, Storage storage) {
        return taskList.listTasks();
    }
}
