package duke.command;

import duke.Duke;
import duke.util.DukeDB;
import duke.util.DukeException;
import duke.util.DukeConfig;
import duke.util.DukeTaskList;
import duke.util.Ui;

import java.util.Map;


/**
 * The ByeCommand class encapsulates the run method for the bye command.
 */
public class ByeCommand implements DukeActions {


    /**
     * Performs the actions for the ByeCommand when activated
     * @param map The parsed command
     * @param list The tasklist
     * @param database The database to write to
     * @param config The configuration settings
     * @param ui The UI object to interact with
     * @return boolean to indicate the end of the listen operation
     * @throws DukeException When erroneous inputs are given.
     */
    @Override
    public boolean runAndCanContinue(Map<String, String> map, DukeTaskList list, DukeDB database, DukeConfig config, Ui ui)
            throws DukeException {
        Duke.printMsg("Bye. Hope to see you again soon!");
        return false;
    }
}
