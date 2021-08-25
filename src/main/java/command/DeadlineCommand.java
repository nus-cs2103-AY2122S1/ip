package command;

import core.Storage;
import core.TaskList;
import tasks.Deadline;

public class DeadlineCommand extends Command {
    private Deadline deadlineTask;

    public DeadlineCommand(String taskName, String time) {
        deadlineTask = new Deadline(taskName, time);
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.addTask(deadlineTask);
        storage.addTasksToFile(taskList);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}