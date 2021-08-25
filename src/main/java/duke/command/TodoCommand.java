package duke.command;

import duke.TaskList;
import duke.CommandResult;
import duke.task.Task;
import duke.task.Todo;

/**
 * A command that can add a Deadline task to a TaskList.
 */
public class TodoCommand extends Command implements TaskListAddable {

    /** Class level constant that signifies the command used to invoke this. */
    public static final String COMMAND_WORD = "todo";

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
     * Overrides execute() from Command and returns a CommandResult which stores the feedback string
     * to be returned to the UserInterface.
     * @return CommandResult to be rendered by UserInterface.
     */
    @Override
    public CommandResult execute() {
        TaskList taskList = super.getTaskList();
        Task todo = new Todo(this.command, false);
        String feedback = addTaskToTaskList(taskList, todo);
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
