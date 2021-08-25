package duke.command;

import duke.task.Event;
import duke.task.Task;
import duke.util.*;

import java.util.Map;


/**
 * The EventCommand class encapsulates the run method for the event command.
 */
public class EventCommand implements DukeActions {



    /**
     * Performs the actions for the Event Command when activated
     * @param map The parsed command
     * @param list The tasklist
     * @param database The database to write to
     * @param config The configuration settings
     * @param ui The UI object to interact with
     * @return boolean to indicate the end of the listen operation
     * @throws DukeException When erroneous inputs are given.
     */
    @Override
    public boolean run(Map<String, String> map, DukeTaskList list, DukeDB database, DukeConfig config, Ui ui)
            throws DukeException {
        if (!map.containsKey("/at")) {
            throw new DukeException("Missing positional argument '/at'.");
        } else if (map.get("event") == null || map.get("/at") == null) {
            throw new DukeException("duke.task.Event body or /at body cannot be empty.");
        } else {
            Task event = new Event(map.get("event"),
                    map.get("/at"));
            list.addTask(event)
                    .orElseThrow(() -> new DukeException("Failed to add the task to the list"));
            ui.addTaskUpdate(event,
                    list.getSize());
        }
        return true;
    }
}
