package duke.command;
import duke.main.DukeException;
import duke.main.Storage;
import duke.task.TaskList;
import duke.main.Ui;

/**
 * Represents a command to filter out tasks that fall on a specific date.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class FindCommand extends Command {
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final String KEYWORD = "find ";
    private String searchPhrase;
    private final String EXECUTION_MESSAGE = "Here are the matching tasks in your list:\n";
    /**
     * Class constructor.
     *
     * @param userCommand to filter out tasks containing a searchPhrase.
     * @throws StringIndexOutOfBoundsException if there is no search phrase.
     */
    public FindCommand(String userCommand) throws DukeException {
        super();
        int startingIndex = findStartingIndex(userCommand);
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
        return ui.showMatchingTasks(matchingTasks, EXECUTION_MESSAGE);
    }
}
