package duke.command;
import duke.main.Storage;
import duke.main.TaskList;
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
    /**
     * Class constructor.
     *
     * @param userCommand to filter out tasks containing a searchPhrase.
     * @throws StringIndexOutOfBoundsException if there is no search phrase.
     */
    public FindCommand(String userCommand) throws StringIndexOutOfBoundsException {
        super();
        int startingIndex = findStartingIndex(userCommand);
        this.searchPhrase = getSearchPhrase(userCommand, startingIndex);
        if (searchPhrase.equals("")) {
            throw new StringIndexOutOfBoundsException();
        }
    }
    private int findStartingIndex(String userCommand) {
        return userCommand.indexOf(KEYWORD) + KEYWORD.length();
    }
    private String getSearchPhrase(String userInput, int startingIndex) {
        return userInput.substring(startingIndex);
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
        String executionMessage = generateExecutionMessage();
        return ui.showMatchingTasks(matchingTasks, searchPhrase, executionMessage, tasks.getNumTasks());
    }
    private String generateExecutionMessage() {
        return "Here are the matching tasks in your list:\n";
    }
}
