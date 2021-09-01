package duke.commands;

import duke.TaskList;

public class ListCommand implements Command {

    @Override
    public String execute(TaskList taskList) {
        return taskList.getList();
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
