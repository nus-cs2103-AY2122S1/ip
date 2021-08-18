package commands;

import core.TaskList;
import tasks.Deadline;

public class DeadlineCommand extends Command {
    private Deadline deadlineTask;

    public DeadlineCommand(String taskName, String date) {
        deadlineTask = new Deadline(taskName, date);
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