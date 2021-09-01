package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.DukeException;

/**
 * Command to search for a Task matching a search term.
 *
 * @author Quan Teng Foong
 */
public class SearchCommand extends Command {

    public SearchCommand(String searchTerm) {
        super(searchTerm);
    }

    /**
     * Searches current TaskList for a search term.
     *
     * @param taskList the current list of tasks
     * @param ui the user interface object
     * @param storage the storage object
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList matchingTasks = taskList.search(super.getExtraInput());
        return "Here are your search results:\n" + matchingTasks;
    }
}
