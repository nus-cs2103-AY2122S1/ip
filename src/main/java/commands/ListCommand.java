package commands;

import core.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList) {
        taskList.listTasks();
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
