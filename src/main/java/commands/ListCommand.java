package commands;
import tasks.TaskList;

/**
 * This is an AddDeadlineCommand Class, which inherits from Command.
 * The execution of this command will output the current list of tasks
 * to the user.
 */
public class ListCommand extends Command {
    public static final String KEYWORD = "list";

    /**
     * Return list of tasks.
     * @param taskList The existing list of tasks.
     * @return The list of tasks.
     */
    public String execute(TaskList taskList) {
        return "Here are the tasks in your list:\n" + taskList.toString();
    }
}
