package duke.command;

import duke.TaskList;
import duke.CommandResult;
import duke.DukeException;
import duke.task.Deadline;
import duke.task.Task;

/**
 * A command that can add a Deadline task to a TaskList.
 */
public class DeadlineCommand extends Command implements TaskListAddable {

    /** The rest of the command input by the user passed on by duke*/
    private final String command;

    /** Class level constant that signifies the command used to invoke this. */
    public static final String COMMAND_WORD = "deadline";

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
     * @throws DukeException for any incorrect commands input by the user.
     */
    @Override
    public CommandResult execute() throws DukeException {
        TaskList taskList = super.getTaskList();
        String[] eventList = this.command.split(" /by ");
        if (eventList.length != 2) {
            throw new DukeException("Incorrect command was given for deadline. " + "Try this: deadline name_here" +
                    " /at date_here");
        }
        Task event = new Deadline(eventList[0], eventList[1], false);
        String feedback = addTaskToTaskList(taskList, event);
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
