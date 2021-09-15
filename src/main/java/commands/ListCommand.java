package commands;
import exceptions.MorganException;
import storage.Storage;
import tasks.TaskList;

/**
 * This is a ListCommand Class, which inherits from Command.
 * The execution of this command will output the current list of tasks
 * to the user.
 */
public class ListCommand extends Command {
    public static final String KEYWORD = "list";
    public static final String INPUT_FORMAT = String.format("\t%s", KEYWORD);
    private static final String EMPTY_LIST_ERROR = "There is no existing task.";

    /**
     * Return list of tasks.
     * @param tasks The existing list of tasks.
     * @param storage The storage object to store task data.
     * @return The list of tasks.
     * @throws MorganException If the list is empty.
     */
    public String execute(TaskList tasks, Storage storage) throws MorganException {
        assert tasks != null && storage != null;
        if (tasks.isEmpty()) {
            throw new MorganException(EMPTY_LIST_ERROR);
        }
        return "Here are the tasks in your list:\n" + tasks;
    }
}
