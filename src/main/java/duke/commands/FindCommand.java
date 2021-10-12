package duke.commands;

import duke.util.PersistentStorage;
import duke.util.Response;
import duke.util.Tasklist;

/**
 * Class encapsulating a "find" command by the user.
 */
public class FindCommand extends Command {

    /** Starting index of search term(s) */
    public static final int SEARCH_TERM_START_IDX = 5;

    /** String containing the desired search terms */
    private String searchTerms;


    /**
     * A constructor for a FindCommand
     *
     * @param searchTerms A String representing the search terms by the user
     */
    public FindCommand(String searchTerms) {
        this.searchTerms = searchTerms;
    }

    /**
     * Executes a find command by finding task descriptions that contain the keywords
     * and printing said tasks.
     *
     * @param taskList The Tasklist associated with the Duke instance
     * @param response The UI associated with the Duke instance
     * @param storage The PersistentStorage associated with the Duke instance
     * @return A Commandresult detailing the results of the find operation.
     */
    public CommandResult executeCommand(Tasklist taskList, Response response, PersistentStorage storage) {
        Tasklist matchingTasks = taskList.findAllTasksWith(this.searchTerms);
        return new CommandResult(response.showMatchingTasks(matchingTasks));
    }
}
