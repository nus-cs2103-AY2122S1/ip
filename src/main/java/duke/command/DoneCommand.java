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

    /** Class level constant that represents the action taken by the command. */
    public static final String DESCRIPTION = "Complete a task from the taskList";

    /** Class level constant that represents the format needed to successfully complete command. */
    public static final String FORMAT = COMMAND_WORD + " id_of_task_to_be_completed";

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
     * Returns the string representation of the command description and format.
     * @return String that represents the command description and format.
     */
    public static String formatAndDescription() {
        return COMMAND_WORD + ": " + DESCRIPTION + "\n" + FORMAT;
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
