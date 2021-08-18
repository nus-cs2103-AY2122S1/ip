package commands;

import tasks.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(String userInput) {
        super(userInput);
        this.index = this.getIndex();
    }

    @Override
    public void updateLogAndTaskList(TaskList oldTaskList) {
        //Update log
        this.newLog += "Noted. I've removed this task:\n";
        this.newLog += oldTaskList.getTask(index) + "\n";
        this.newLog += utils.Util.informNumTask(oldTaskList.getNumTask() - 1);

        //Update TaskList
        oldTaskList.markAsDone(this.index);
        this.newTaskList = oldTaskList;

        //Delete the task
        oldTaskList.deleteTask(index);
        this.newTaskList = oldTaskList;
    }

    public int getIndex() {
        return Integer.parseInt(this.userInputList.get(1)) - 1;
    }
}

