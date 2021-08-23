package commands;

import tasks.Task;
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
        //Write your task List to Files
        Util.writeToFile("./data/duke.txt", oldTaskList.saveFormat());
    }
}
