package commands;

import tasks.TaskList;

public class InvalidCommand extends Command {
    public InvalidCommand() {
        super("");
    }

    @Override
    public void updateLogAndTaskList(TaskList oldTaskList) {
        this.newTaskList = oldTaskList;
        this.newLog = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
