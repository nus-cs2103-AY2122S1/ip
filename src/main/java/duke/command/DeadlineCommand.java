package duke.command;

import duke.task.Deadline;
import duke.task.Task;
import duke.util.*;

import java.time.LocalDateTime;
import java.util.Map;


/**
 * The DeadlineCommand class encapsulates the run method for the deadline command.
 */
public class DeadlineCommand implements DukeActions {

    /**
     * Performs the actions for the Deadline Command when activated
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
        if (!map.containsKey("/by")) {
            throw new DukeException("Missing positional argument " + "'/by'.");
        } else if (map.get("deadline") == null || map.get("/by") == null) {
            throw new DukeException("Deadline body cannot be empty.");
        } else {
            LocalDateTime by = Parser.parseDateTime(map.get("/by"),
                    config)
                    .orElseThrow(() -> new DukeException("Invalid" + " date time specified"));
            Task event = new Deadline(map.get("deadline"),
                    by);
            list.addTask(event)
                    .orElseThrow(() -> new DukeException("Failed to add the task to the list"));
            ui.addTaskUpdate(event,
                    list.getSize());
        }
        return true;
    }
}
