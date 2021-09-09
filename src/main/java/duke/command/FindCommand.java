package duke.command;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import duke.task.Task;
import duke.util.DukeConfig;
import duke.util.DukeDB;
import duke.util.DukeException;
import duke.util.DukeTaskList;
import duke.util.Ui;


/**
 * The FindCommand class encapsulates the run method for the find command.
 */
public class FindCommand implements DukeActions {

    /**
     * Performs the actions for the Find Command when activated
     *
     * @param map      The parsed command
     * @param list     The tasklist
     * @param database The database to write to
     * @param config   The configuration settings
     * @param ui       The UI object to interact with
     * @return boolean to indicate the end of the listen operation
     * @throws DukeException When erroneous inputs are given.
     */
    @Override
    public Optional<String> run(Map<String, String> map, DukeTaskList list, DukeDB database, DukeConfig config, Ui ui)
            throws DukeException {
        if (!map.containsKey("find")) {
            throw new DukeException("Find is missing positional argument 'keyword'.");
        } else {
            String filter = map.get("find");
            List<Task> filteredList = list.toFind(filter);
            return Optional.of(ui.findUpdate(filteredList));
        }
    }
}
