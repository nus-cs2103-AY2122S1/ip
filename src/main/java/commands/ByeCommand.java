package commands;

import tasks.TaskList;
import utils.Util;

public class ByeCommand extends Command {
    public ByeCommand() {
        super("");
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void updateLogAndTaskList(TaskList oldTaskList) {
        return;
    }
}
