package duke.command;

import duke.CommandResult;
import duke.DukeException;
import duke.task.Task;
import duke.TaskList;

public class DoneCommand extends Command{

    private final int taskId;
    public static final String COMMAND_WORD = "done";

    public DoneCommand(TaskList taskList, int taskId) {
        super(taskList);
        this.taskId = taskId;
    }

    @Override
    public CommandResult execute() throws DukeException {
        TaskList taskList = super.getTaskList();
        Task completedTask = taskList.markAsCompleted(this.taskId);
        return new CommandResult("Nice! I've marked this task as done:\n "
                + completedTask.getDetails(), false);
    }
}
