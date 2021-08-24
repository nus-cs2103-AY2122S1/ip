package duke.command;

import duke.task.Deadline;
import duke.task.Task;
import duke.util.*;

import java.time.LocalDateTime;
import java.util.Map;

public class DeadlineCommand implements DukeActions {
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
