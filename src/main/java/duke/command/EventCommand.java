package duke.command;

import duke.commandresult.CommandResult;
import duke.exception.IncorrectEventParameterException;
import duke.exception.TimedTaskDateInputException;
import duke.task.Event;
import duke.task.Task;
import duke.task.TimedTask;
import duke.tasklist.TaskList;

/**
 * A command that can add a Deadline task to a TaskList.
 */
public class EventCommand extends Command implements TaskListAddable {

    /** Class level constant that signifies the command used to invoke this. */
    public static final String COMMAND_WORD = "event";

    /** Class level constant that represents the action taken by the command. */
    public static final String DESCRIPTION = "Add an event task and specify the date to complete it by.";

    /** Class level constant that represents the format needed to successfully complete command. */
    public static final String FORMAT = COMMAND_WORD
            + " name_here /at datetime_here ("
            + TimedTask.TIMED_TASK_YEAR_FORMAT
            + " "
            + TimedTask.TIMED_TASK_TIME_FORMAT
            + ")";

    /** The rest of the command input by the user passed on by duke*/
    private final String command;

    /**
     * Constructor that creates EventCommand.
     * @param taskList The TaskList to be given by Duke.
     * @param command The entire command input by the user passed on by Duke.
     */
    public EventCommand(TaskList taskList, String command) {
        super(taskList);
        this.command = command;
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
     * @throws IncorrectEventParameterException for any incorrect event params input by the user.
     * @throws TimedTaskDateInputException if the date given was the wrong format.
     */
    @Override
    public CommandResult execute() throws IncorrectEventParameterException, TimedTaskDateInputException {
        TaskList taskList = super.getTaskList();
        int numOfTasks = taskList.size();
        String[] eventList = this.command.split(" /at ");
        if (eventList.length != 2) {
            throw new IncorrectEventParameterException();
        }
        Task event = new Event(eventList[0], eventList[1], false);
        String feedback = addTaskToTaskList(taskList, event);
        assert numOfTasks + 1 == taskList.size();
        return new CommandResult(feedback, false);
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
