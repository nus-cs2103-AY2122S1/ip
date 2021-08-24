package duke.command;

import duke.task.Task;
import duke.task.Todo;
import duke.util.*;

import java.util.Map;

public class TodoCommand implements DukeActions {
    @Override
    public boolean run(Map<String, String> map, DukeTaskList list, DukeDB database, DukeConfig config, Ui ui)
            throws DukeException {
        if (map.get("todo") == null) {
            throw new DukeException("duke.task.Todo body cannot be empty.");
        } else {
            Task event = new Todo(map.get("todo"));
            list.addTask(event)
                    .orElseThrow(() -> new DukeException("Failed to add the task to the list"));
            ui.addTaskUpdate(event,
                    list.getSize());
        }
        return true;

    }
}
