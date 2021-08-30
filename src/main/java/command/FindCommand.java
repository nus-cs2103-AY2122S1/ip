package command;

import java.util.List;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import task.Task;

/**
 * Command to find tasks by searching for a keyword.
 *
 * @author felix-ong
 */
public class FindCommand extends Command {
    private String keyword;
    private StringBuilder sb;

    /**
     * Constructor for FindCommand.
     * @param keyword The word to search for in the task description.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
        this.sb = new StringBuilder("Here are the matching tasks in your list:\n");
    }

    /**
     * Executes the specific actions for this command.
     *
     * @param tasks   Handles the list of tasks.
     * @param storage Handles the saving and loading of tasks.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        List<Task> matches = tasks.findTask(this.keyword);
        for (int i = 0; i < matches.size(); i++) {
            sb.append(String.format(" %d. %s%n", i + 1, matches.get(i)));
        }
    }

    /**
     * Returns true if the command calls for the program to exit,
     * false otherwise.
     *
     * @return true if command calls for the program to exit, false otherwise.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
