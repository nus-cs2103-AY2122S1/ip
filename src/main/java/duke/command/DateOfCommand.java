package duke.command;

import duke.exceptions.DucIncompleteException;
import duke.exceptions.DucSyntaxErrorException;
import duke.main.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class DateOfCommand extends Command {

    private static final String DEADLINE = "Deadline";
    private static final String EVENT = "Event";
    private final String description;
    private final TaskList taskList;

    /**
     * Constructor for the DateOfCommand class
     * @param description description of command
     * @param taskList task list to be modified
     */
    public DateOfCommand(String description, TaskList taskList) {
        this.description = description;
        this.taskList = taskList;
    }

    @Override
    public String reply() {
        if (description.length() == 0) {
            throw new DucIncompleteException();
        }
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(description);
        } catch (NumberFormatException e) {
            throw new DucSyntaxErrorException(description);
        }
        Task taskName = taskList.get(taskIndex);
        String taskType;
        if (taskName instanceof Deadline) {
            taskType = DEADLINE;
            return "The " + taskType + " time of task "
                    + taskIndex + " is " + taskName.getDate();
        } else if (taskName instanceof Event) {
            taskType = EVENT;
            return "The " + taskType + " time of task "
                    + taskIndex + " is " + taskName.getDate();
        } else if (taskName instanceof Todo) {
            return taskName.getDate() + taskIndex;
        } else {
            throw new DucSyntaxErrorException(taskName.toString());
        }
    }
}
