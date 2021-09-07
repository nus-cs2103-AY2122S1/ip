package duke.command;

import duke.commandresult.CommandResult;
import duke.exception.DukeException;
import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * A command that can delete a task from a taskList.
 */
public class DeleteCommand extends Command implements ListNumberPrintable {

    /** Class level constant that signifies the command used to invoke this. */
    public static final String COMMAND_WORD = "delete";

    /** The id of the task to delete from the list. */
    private final int taskId;

    /**
     * Constructor that creates DeleteCommand.
     * @param taskList The TaskList to be given by Duke.
     * @param taskId The id of the task to be deleted.
     */
    public DeleteCommand(TaskList taskList, int taskId) {
        super(taskList);
        this.taskId = taskId;
    }

    /**
     * Overrides execute() from Command and returns a CommandResult which stores the feedback string
     * to be returned to the UserInterface.
     * @return CommandResult to be rendered by UserInterface.
     * @throws DukeException for any incorrect commands input by the user.
     */
    @Override
    public CommandResult execute() throws DukeException {
        TaskList taskList = super.getTaskList();
        int numOfTasks = taskList.size();
        Task deletedTask = taskList.deleteTask(this.taskId);
        assert numOfTasks - 1 == taskList.size();
        return new CommandResult("Noted. I've removed this task:\n "
                + deletedTask.getDetails()
                + "\n"
                + printListNumber(taskList),
                false);
    }
}
