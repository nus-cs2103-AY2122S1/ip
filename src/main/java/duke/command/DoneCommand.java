package duke.command;

import duke.util.DukeDB;
import duke.util.DukeException;
import duke.util.DukeConfig;
import duke.util.DukeTaskList;
import duke.util.Parser;
import duke.util.Ui;

import java.util.Map;

public class DoneCommand implements DukeActions {
    @Override
    public boolean run(Map<String, String> map, DukeTaskList list, DukeDB database, DukeConfig config, Ui ui)
            throws DukeException {

        list.setDone(Parser.parseInt(map.get("done"))
                .orElseThrow(() -> new DukeException("Positional argument 'index' is not valid.")) - 1)
                .map(x -> {
                    ui.completeTaskUpdate(x,
                            list.getSize());
                    return x;
                })
                .orElseThrow(() -> new DukeException("The provided index is out of bounds."));
        return true;
    }
}
