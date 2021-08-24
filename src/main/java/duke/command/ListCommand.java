package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand implements Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printAll(taskList.getTasks(), "Here are the tasks in your list:");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}