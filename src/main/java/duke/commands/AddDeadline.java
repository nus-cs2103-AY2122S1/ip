package duke.commands;

import duke.exceptions.DuplicateTaskException;
import duke.tasks.Deadline;
import duke.utils.TaskList;

/**
 * Encapsulates a command to add a deadline task. Executing it will return
 * the specified deadline instance.
 */
public class AddDeadline extends Command {
    private String taskName;
    private String deadline;

    /**
     * Creates a command to create a deadline task with the given
     * name and deadline.
     *
     * @param taskName Name of the deadline task.
     * @param deadline Deadline of the task with the format YYYY-MM-DD.
     */
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
