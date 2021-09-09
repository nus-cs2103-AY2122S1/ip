package duke.command;

import java.util.Map;
import java.util.Optional;

import duke.task.Task;
import duke.task.Todo;
import duke.util.DukeConfig;
import duke.util.DukeDB;
import duke.util.DukeException;
import duke.util.DukeTaskList;
import duke.util.Ui;


/**
 * The TodoCommand class encapsulates the run method for the todo command.
 */
public class TodoCommand implements DukeActions {

    /**
     * Performs the actions for the Todo Command when activated
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
        if (map.get("todo") == null) {
            throw new DukeException("duke.task.Todo body cannot be empty.");
        } else {
            Task event = new Todo(map.get("todo"));
            list.addTask(event)
                    .orElseThrow(() -> new DukeException("Failed to add the task to the list"));
            return Optional.of(ui.addTaskUpdate(event,
                    list.getSize()));
        }
    }
}
