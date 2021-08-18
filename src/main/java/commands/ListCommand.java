package commands;

import tasks.TaskList;

public class ListCommand extends Command {
    public ListCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void updateLogAndTaskList(TaskList oldTaskList) {
        //Update log
        this.newLog += "Here are the tasks in your list:\n";
        this.newLog += oldTaskList;

        //TaskList Unchanged
        this.newTaskList = oldTaskList;
    }
}