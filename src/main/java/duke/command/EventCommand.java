package duke.command;

import duke.commandresult.CommandResult;
import duke.exception.IncorrectEventParameterException;
import duke.exception.TimedTaskDateInputException;
import duke.task.Event;
import duke.task.Task;
import duke.tasklist.TaskList;

/**
 * A command that can add a Deadline task to a TaskList.
 */
public class EventCommand extends Command implements TaskListAddable {

    /** Class level constant that signifies the command used to invoke this. */
    public static final String COMMAND_WORD = "event";

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
