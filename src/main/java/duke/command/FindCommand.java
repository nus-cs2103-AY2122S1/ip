package duke.command;

import duke.task.Task;
import duke.util.*;

import java.util.List;
import java.util.Map;

public class FindCommand implements DukeActions {
    @Override
    public boolean run(Map<String, String> map, DukeTaskList list, DukeDB database, DukeConfig config, Ui ui)
            throws DukeException {
        if (!map.containsKey("find")) {
            throw new DukeException("Find is missing positional argument 'keyword'.");
        } else {
            String filter = map.get("find");
            List<Task> filteredList = list.toFind(filter);
            ui.findUpdate(filteredList);
        }
        return true;
    }
}
