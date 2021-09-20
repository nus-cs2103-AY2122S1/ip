package duke.command;
import duke.main.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.task.TaskList;

/**
 * Represents a command to filter out tasks that fall on a specific date.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class FindCommand extends Command {
    private static final String KEYWORD = "find ";
    private static final String EXECUTION_MESSAGE = "Here are the matching tasks in your list:\n";
    private String searchPhrase;
    /**
     * Class constructor.
     *
     * @param userCommand to filter out tasks containing a searchPhrase.
     * @throws StringIndexOutOfBoundsException if there is no search phrase.
     */
    public FindCommand(String userCommand) throws DukeException {
        super();
        assert !isExit() : "isExit should return false";
        int startingIndex = findStartingIndex(userCommand);
        assert startingIndex != -1 : "starting index should not be -1, the keyword should be in userCommand";
        this.searchPhrase = getSearchPhrase(userCommand, startingIndex);
        if (searchPhrase.equals("")) {
            throw new DukeException(DukeException.Exceptions.StringIndexOutOfBoundsException);
        }
    }
    private int findStartingIndex(String userCommand) {
        return userCommand.indexOf(KEYWORD) + KEYWORD.length();
    }
    private String getSearchPhrase(String userCommand, int startingIndex) {
        return userCommand.substring(startingIndex);
    }
    /**
     * Executes a command to filter out tasks falling on the specified date.
     * @param tasks lists of tasks
     * @param ui the user interface.
     * @param storage the storage file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findMatchingTasks(searchPhrase);
        assert matchingTasks != null : "matching tasks cannot be null";
        return ui.showMatchingTasks(matchingTasks, EXECUTION_MESSAGE);
    }
}
