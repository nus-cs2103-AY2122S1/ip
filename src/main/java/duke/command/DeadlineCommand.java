package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.task.Deadline;

public class DeadlineCommand extends Command {
    private Deadline deadlineTask;

    public DeadlineCommand(String taskName, String time) {
        deadlineTask = new Deadline(taskName, time);
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.addTask(deadlineTask);
        storage.saveTasksToFile(taskList);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}