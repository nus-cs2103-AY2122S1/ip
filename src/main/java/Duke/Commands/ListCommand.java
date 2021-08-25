package Duke.Commands;

import Duke.Storage.Storage;
import Duke.Task.TaskList;
import Duke.Ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super(CommandType.LIST, false);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList);
    }
}
