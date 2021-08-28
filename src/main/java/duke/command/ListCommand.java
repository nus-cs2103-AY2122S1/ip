package duke.command;

import duke.TaskList;
import duke.Ui;

public class ListCommand implements Command {

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.stringWithDivider(taskList.getList());
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
