package duke.commands;

import duke.exceptions.DuplicateTaskException;
import duke.tasks.Deadline;
import duke.utils.TaskList;

public class AddDeadline extends Command {
    private String taskName;
    private String deadline;

    public AddDeadline(String taskName, String deadline) {
        this.taskName = taskName;
        this.deadline = deadline;
    }

    @Override
    public TaskList execute(TaskList taskList) throws DuplicateTaskException {
        Deadline deadline = new Deadline(this.taskName, this.deadline);
        taskList.add(deadline);
        return taskList;
    }
}