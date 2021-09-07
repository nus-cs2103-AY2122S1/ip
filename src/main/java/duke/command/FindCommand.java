package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Represents a duke.command to filter out tasks that fall on a specific date.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class FindCommand extends Command {
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final String KEY_WORD = "find ";
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final String SEARCH_PHRASE;

    /**
     * Class constructor.
     *
     * @param userCommand to filter out tasks containing a searchPhrase.
     * @throws StringIndexOutOfBoundsException if there is no search phrase.
     */
    public FindCommand(String userCommand) throws StringIndexOutOfBoundsException {
        int startingIndex = userCommand.indexOf(KEY_WORD) + KEY_WORD.length();
        this.SEARCH_PHRASE = userCommand.substring(startingIndex);
        if (SEARCH_PHRASE.equals("")) {
            throw new StringIndexOutOfBoundsException();
        }
    }

    /**
     * Executes a duke.command to filter out tasks falling on the specified date.
     *
     * @param tasks   lists of tasks
     * @param ui      the user interface.
     * @param storage the storage file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matchingTasks = tasks.findMatchingTasks(SEARCH_PHRASE);
        String message = "Here are the matching tasks in your list:";
        ui.showMatchingTasks(matchingTasks, message);
    }
}
