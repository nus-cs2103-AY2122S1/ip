package duke.command;

import duke.TaskList;
import duke.CommandResult;
import duke.DukeException;
import duke.task.Task;

public class DeleteCommand extends Command implements ListNumberPrintable {

    private final int taskId;
    public static final String COMMAND_WORD = "delete";

    public DeleteCommand(TaskList taskList, int taskId) {
        super(taskList);
        this.taskId = taskId;
    }

    @Override
    public String printListNumber(TaskList taskList) {
        return "You now have "
                + taskList.size() + " tasks in the list.";
    }

    @Override
    public CommandResult execute() throws DukeException {
        TaskList taskList = super.getTaskList();
        Task deletedTask = taskList.deleteTask(this.taskId);
        return new CommandResult("Noted. I've removed this task:\n "
                + deletedTask.getDetails()
                + "\n"
                + printListNumber(taskList),
                false);
    }
}
