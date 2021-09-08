package duke.command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Class to find tasks using a search keyword.
 */
public class FindCommand implements Command {

    private String keyword;
    private List<Task> filtered;

    /**
     * Constructor to initialize Find Command.
     *
     * @param keyword Keyword to search for relevant tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
        this.filtered = new ArrayList<>();
    }

    /**
     * Constructor to initialize FindCommand.
     */
    public FindCommand() {}

    /**
     * Method to execute command.
     *
     * @param taskList TaskList that manages all current tasks.
     * @param ui Ui used to print messages.
     * @param storage Loads and saves the tasks to a txt file.
     * @throws DukeException Thrown if there are input/parsing errors.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        List<Task> filteredTasks = taskList
                .getTasks()
                .stream()
                .filter(task -> task.getDetail().toLowerCase().contains(keyword))
                .collect(Collectors.toList());
        filtered = filteredTasks;
        ui.printAll(filteredTasks, "Here are the matching tasks in your list");
    }


    /**
     * Returns a boolean to determine if Duke should stop running.
     *
     * @return A boolean false as this is not an exit command.
     */
    @Override
    public boolean isRunning() {
        return false;
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "find <keyword> [prints a list of tasks that match the keyword]";
    }
}
