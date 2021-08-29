package duke.commands;

import duke.TaskList;
import duke.Ui;

public class ListCommand implements Command {

    @Override
    public String execute(TaskList taskList, Ui ui) {
        return taskList.getList();
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
