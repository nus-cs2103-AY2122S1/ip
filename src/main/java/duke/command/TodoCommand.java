package duke.command;

import duke.commandresult.CommandResult;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;

/**
 * A command that can add a Deadline task to a TaskList.
 */
public class TodoCommand extends Command implements TaskListAddable {

    /** Class level constant that signifies the command used to invoke this. */
    public static final String COMMAND_WORD = "todo";

    /** Class level constant that represents the action taken by the command. */
    public static final String DESCRIPTION = "Add a Todo task.";

    /** Class level constant that represents the format needed to successfully complete command. */
    public static final String FORMAT = COMMAND_WORD
            + " name_here";

    /** The rest of the command input by the user passed on by duke*/
    private final String command;

    /**
     * Constructor that creates TodoCommand.
     * @param taskList The TaskList to be given by Duke.
     * @param command The entire command input by the user passed on by Duke.
     */
    public TodoCommand(TaskList taskList, String command) {
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
     */
    @Override
    public CommandResult execute() {
        TaskList taskList = super.getTaskList();
        int numOfTasks = taskList.size();
        Task todo = new Todo(this.command, false);
        String feedback = addTaskToTaskList(taskList, todo);
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
