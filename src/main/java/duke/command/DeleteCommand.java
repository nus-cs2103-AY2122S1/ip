package duke.command;

import java.util.Map;
import java.util.Optional;

import duke.util.DukeConfig;
import duke.util.DukeDB;
import duke.util.DukeException;
import duke.util.DukeTaskList;
import duke.util.Parser;
import duke.util.Ui;

public class DeleteCommand implements DukeActions {


    /**
     * Performs the actions for the Delete Command when activated
     *
     * @param map      The parsed command
     * @param list     The tasklist
     * @param database The database to write to
     * @param config   The configuration settings
     * @param ui       The UI oject to interact with
     * @return boolean to indicate the end of the listen operation
     * @throws DukeException When erroneous inputs are given.
     */
    @Override
    public Optional<String> run(Map<String, String> map, DukeTaskList list, DukeDB database, DukeConfig config, Ui ui)
            throws DukeException {
        return Optional.of(list.toRemove(Parser.parseInt(map.get("delete"))
                .orElseThrow(() -> new DukeException("The delete command is either missing the positional argument "
                        + "'index' or it " + "is invalid.")) - 1)
                .map(x -> {
                    return ui.removeTaskUpdate(x,
                            list.getSize());
                })
                .orElseThrow(() -> new DukeException("Failed to remove task from the tasklist.")));

    }
}
