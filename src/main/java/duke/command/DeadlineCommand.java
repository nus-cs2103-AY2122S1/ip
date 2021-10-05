package duke.command;

import duke.commandresult.CommandResult;
import duke.exception.IncorrectDeadlineParameterException;
import duke.exception.TimedTaskDateInputException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TimedTask;
import duke.tasklist.TaskList;

/**
 * A command that can add a Deadline task to a TaskList.
 */
public class DeadlineCommand extends Command implements TaskListAddable {

    /** Class level constant that signifies the command used to invoke this. */
    public static final String COMMAND_WORD = "deadline";

    /** Class level constant that represents the action taken by the command. */
    public static final String DESCRIPTION = "Add a deadline task and specify the date to complete it by.";

    /** Class level constant that represents the format needed to successfully complete command. */
    public static final String FORMAT = COMMAND_WORD
            + " name_here /by datetime_here ("
            + TimedTask.TIMED_TASK_YEAR_FORMAT
            + " "
            + TimedTask.TIMED_TASK_TIME_FORMAT
            + ")";

    /** The rest of the command input by the user passed on by duke*/
    private final String command;

    /**
     * Constructor that creates DeadlineCommand.
     * @param taskList The TaskList to be given by Duke.
     * @param command The entire command input by the user passed on by Duke.
     */
    public DeadlineCommand(TaskList taskList, String command) {
        super(taskList);
        this.command = command;
    }

    /**
     * Overrides execute() from Command and returns a CommandResult which stores the feedback string
     * to be returned to the UserInterface.
     * @return CommandResult to be rendered by UserInterface.
     * @throws IncorrectDeadlineParameterException for any incorrect commands input by the user.
     * @throws TimedTaskDateInputException if the date given was wrong.
     */
    @Override
    public CommandResult execute() throws IncorrectDeadlineParameterException, TimedTaskDateInputException {
        TaskList taskList = super.getTaskList();
        int numOfTasks = taskList.size();
        String[] deadlineList = this.command.split(" /by ");
        if (deadlineList.length != 2) {
            throw new IncorrectDeadlineParameterException();
        }
        Task deadline = new Deadline(deadlineList[0], deadlineList[1], false);
        String feedback = addTaskToTaskList(taskList, deadline);
        assert numOfTasks + 1 == taskList.size();
        return new CommandResult(feedback, false);
    }

    /**
     * Returns the string representation of the command description and format.
     * @return String that represents the command description and format.
     */
    public static String formatAndDescription() {
        return COMMAND_WORD + ": " + DESCRIPTION + "\n" + FORMAT;
    }

    /**
     * Overrides addTaskToTaskList(TaskList taskList, Task task) as specified by
     * @param taskList The TaskList.
     * @param task The task to be added.
     * @return String format of the resultant message to be printed.
     */
    @Override
    public String addTaskToTaskList(TaskList taskList, Task task) {
        taskList.addTask(task);
        return "Got it. I've added this task:\n  "
                + task.getDetails()
                + "\n"
                + printListNumber(taskList);
    }
}
