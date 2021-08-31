package commands;
import exceptions.MorganException;
import storage.Storage;
import tasks.TaskList;

/**
 * This is an AddDeadlineCommand Class, which inherits from Command.
 * The execution of this command will output the current list of tasks
 * to the user.
 */
public class ListCommand extends Command {
    public static final String KEYWORD = "list";
    private final String EMPTY_LIST_ERROR = "There is no existing task.";

    /**
     * Return list of tasks.
     * @param taskList The existing list of tasks.
     * @return The list of tasks.
     */
    public String execute(TaskList taskList, Storage storage) throws MorganException {
        if (taskList.isEmpty()) {
            throw new MorganException(EMPTY_LIST_ERROR);
        }
        return "Here are the tasks in your list:\n" + taskList;
    }
}
