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
    private final String KEYWORRD = "find ";
    private String searchPhrase;
    private boolean isExitCommand;
    /**
     * Class constructor.
     *
     * @param userCommand to filter out tasks containing a searchPhrase.
     * @throws StringIndexOutOfBoundsException if there is no search phrase.
     */
    public FindCommand(String userCommand) throws StringIndexOutOfBoundsException {
        int startingIndex = findStartingIndex(userCommand);
        this.searchPhrase = userCommand.substring(startingIndex);
        if (searchPhrase.equals("")) {
            throw new StringIndexOutOfBoundsException();
        }
        isExitCommand = false;
    }
    private int findStartingIndex(String userCommand) {
        return userCommand.indexOf(KEYWORRD) + KEYWORRD.length();
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
        String message = generateExecutionMessage();
        return ui.showMatchingTasks(matchingTasks, searchPhrase, message);
    }
    private String generateExecutionMessage() {
        return "Here are the matching tasks in your list:\n";
    }
}
