package duke.command;

import duke.exception.DukeException;
import duke.util.Store;
import duke.util.Tasklist;
import duke.util.Ui;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: A-CodingStandard. Modify the code to comply with a given coding standard
 *
 * Description:
 * Class that encapsulates the delete task command inputted by the user
 *
 * @author Keith Tan
 */
public class FindCommand extends Command{
    private final String searchTerm;
    public static final String COMMAND_WORD = "add";

    public FindCommand(String searchTerm) {

        this.searchTerm = searchTerm;

    }

    /**
     * Executes the filter task command. Returns a specific task in the task list that contains search term.
     *
     * @param list Tasklist of current tasks
     * @param ui Ui which prints any successful message from the associated methods
     * @param storage Current Storage of DUke
     * @throws DukeException throws an exception if any Duke Error occurs while running
     *                       the associated methods
     */
    @Override
    public void execute(Tasklist list, Ui ui, Store storage) throws DukeException {
        String successMessage = list.filterTask(searchTerm);
        ui.printMessage(successMessage);

    }
}
