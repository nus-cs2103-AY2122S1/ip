package duke.logic.command;

import duke.logic.tasks.Deadline;
import duke.logic.tasks.TaskList;

public class DeadlineCommand extends Command {
    private Deadline task;

    public DeadlineCommand(Deadline task) {
        this.task = task;
    }

    @Override
    public String executeCommand(TaskList taskList) {
        return taskList.addTask(task);
    }
}
