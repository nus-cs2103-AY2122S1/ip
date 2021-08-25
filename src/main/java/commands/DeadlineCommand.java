package commands;

import core.TaskList;
import tasks.Deadline;

public class DeadlineCommand extends Command {
    private Deadline deadlineTask;

    public DeadlineCommand(String taskName, String time) {
        deadlineTask = new Deadline(taskName, time);
    }

    @Override
    public void execute(TaskList taskList) {
        taskList.addTask(deadlineTask);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}