package duke.command;

import duke.util.DukeDB;
import duke.util.DukeException;
import duke.util.DukeConfig;
import duke.util.DukeTaskList;
import duke.util.Parser;
import duke.util.Ui;

import java.util.Map;

public class DeleteCommand implements DukeActions {
    @Override
    public boolean run(Map<String, String> map, DukeTaskList list, DukeDB database, DukeConfig config, Ui ui)
            throws DukeException {
        list.toRemove(Parser.parseInt(map.get("delete"))
                .orElseThrow(() -> new DukeException("The delete command is either missing the positional argument 'index' or it " + "is invalid.")) - 1)
                .map(x -> {
                    ui.removeTaskUpdate(x, list.getSize());
                    return x;
                })
                .orElseThrow(() -> new DukeException("Failed to remove task from the tasklist."));
        return true;

    }
}
