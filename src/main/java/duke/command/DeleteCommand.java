package duke.command;

import duke.commandresult.CommandResult;
import duke.exception.IncorrectIndexException;
import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * A command that can delete a task from a taskList.
 */
public class DeleteCommand extends Command implements ListNumberPrintable {

    /** Class level constant that signifies the command used to invoke this. */
    public static final String COMMAND_WORD = "delete";

    /** Class level constant that represents the action taken by the command. */
    public static final String DESCRIPTION = "Delete a task from the taskList";

    /** Class level constant that represents the format needed to successfully complete command. */
    public static final String FORMAT = COMMAND_WORD + " id_of_task_to_be_deleted";

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
     * @throws IncorrectIndexException for incorrect index supplied by user.
     */
    @Override
    public CommandResult execute() throws IncorrectIndexException {
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
