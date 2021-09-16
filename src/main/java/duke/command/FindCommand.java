package duke.command;

import duke.exception.DukeException;
import duke.util.Tasklist;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 *
 * Description:
 * Class that encapsulates the delete task command inputted by the user
 *
 * @author Keith Tan
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "add";
    private String searchTerm;
    private Tasklist list;

    /**
     * Constructor for FindCommand.
     * Takes in the search term to filter the tasks and creates the find command
     */
    public FindCommand(Tasklist list, String searchTerm) {

        this.searchTerm = searchTerm;
        this.list = list;

    }

    /**
     * Executes the filter task command. Returns a specific task in the task list that contains search term.
     *
     * @throws DukeException throws an exception if any Duke Error occurs while running
     *                       the associated methods
     */
    @Override
    public String execute() throws DukeException {
        String successMessage = this.list.filterTask(searchTerm);
        return successMessage;

    }
}
