package duke.command;

import duke.util.DukeDB;
import duke.util.DukeException;
import duke.util.DukeConfig;
import duke.util.DukeTaskList;
import duke.util.Ui;

import java.util.Map;


public interface DukeActions {

    public boolean run (Map<String, String> map, DukeTaskList list, DukeDB database, DukeConfig config, Ui ui) throws
            DukeException;
}
