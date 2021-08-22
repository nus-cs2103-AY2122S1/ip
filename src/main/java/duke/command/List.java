package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class List extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.print(taskList.stringifyTasksForList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
