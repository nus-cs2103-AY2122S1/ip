package duke.command;

import duke.Duke;
import duke.util.DukeDB;
import duke.util.DukeException;
import duke.util.DukeConfig;
import duke.util.DukeTaskList;
import duke.util.Ui;

import java.util.Map;

public class ByeCommand implements DukeActions {

    @Override
    public boolean run(Map<String, String> map, DukeTaskList list, DukeDB database, DukeConfig config, Ui ui)
            throws DukeException {
        Duke.printMsg("Bye. Hope to see you again soon!");
        return false;
    }
}
