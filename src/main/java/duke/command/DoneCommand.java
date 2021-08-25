package duke.command;

import duke.CommandResult;
import duke.DukeException;
import duke.task.Task;
import duke.TaskList;

/**
 * A command that can mark a task as completed from a taskList.
 */
public class DoneCommand extends Command{

    /** The id of the task to marked as completed from the list. */
    private final int taskId;

    /** Class level constant that signifies the command used to invoke this. */
    public static final String COMMAND_WORD = "done";

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
     * @throws DukeException for any incorrect commands input by the user.
     */
    @Override
    public CommandResult execute() throws DukeException {
        TaskList taskList = super.getTaskList();
        Task completedTask = taskList.markAsCompleted(this.taskId);
        return new CommandResult("Nice! I've marked this task as done:\n "
                + completedTask.getDetails(), false);
    }
}
