package duke.command;

import duke.commandresult.CommandResult;
import duke.exception.IncorrectIndexException;
import duke.exception.TimedTaskDateInputException;
import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * A command that can mark a task as completed from a taskList.
 */
public class DoneCommand extends Command {

    /** Class level constant that signifies the command used to invoke this. */
    public static final String COMMAND_WORD = "done";

    /** The id of the task to marked as completed from the list. */
    private final int taskId;

    /**
     * Constructor that creates DeleteCommand.
     * @param taskList The TaskList to be given by Duke.
     * @param taskId The id of the task to be marked as completed.
     */
    public DoneCommand(TaskList taskList, int taskId) {
        super(taskList);
        this.taskId = taskId;
    }

    /**
     * Overrides execute() from Command and returns a CommandResult which stores the feedback string
     * to be returned to the UserInterface.
     * @return CommandResult to be rendered by UserInterface.
     * @throws IncorrectIndexException for any incorrect index supplied by user.
     * @throws TimedTaskDateInputException for any incorrect date supplied.
     */
    @Override
    public CommandResult execute() throws IncorrectIndexException, TimedTaskDateInputException {
        TaskList taskList = super.getTaskList();
        int numOfTasks = taskList.size();
        Task completedTask = taskList.markAsCompleted(this.taskId);
        assert numOfTasks == taskList.size();
        return new CommandResult("Nice! I've marked this task as done:\n "
                + completedTask.getDetails(), false);
    }
}
