package commands;

import tasks.TaskList;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(String userInput) {
        super(userInput);
        this.index = this.getIndex();
    }

    @Override
    public void updateLogAndTaskList(TaskList oldTaskList) {
        //Update TaskList
        oldTaskList.markAsDone(this.index);
        this.newTaskList = oldTaskList;

        //Update log
        this.newLog += "Nice! I've marked this task as done:\n";
        this.newLog += oldTaskList.getTask(this.index);
    }

    public int getIndex() {
        return Integer.parseInt(this.userInputList.get(1)) - 1;
    }
}

