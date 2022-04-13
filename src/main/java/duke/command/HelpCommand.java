package duke.command;

import java.util.Map;
import java.util.Optional;

import duke.util.DukeConfig;
import duke.util.DukeDB;
import duke.util.DukeException;
import duke.util.DukeTaskList;
import duke.util.Ui;

public class HelpCommand implements DukeActions {
    /**
     * Performs the actions for the Find Command when activated
     *
     * @param map      The parsed command
     * @param list     The tasklist
     * @param database The database to write to
     * @param config   The configuration settings
     * @param ui       The UI object to interact with
     * @return boolean to indicate the end of the listen operation
     * @throws duke.util.DukeException When erroneous inputs are given.
     */
    @Override
    public Optional<String> run(Map<String, String> map, DukeTaskList list, DukeDB database, DukeConfig config,
                                Ui ui) throws DukeException {
        String helpString = "Welcome to duke. Here is the complete command list:\n"
            + "1. todo_{description} to create a todo with the description\n"
            + "2. deadline_{description}_/by_{date} to create a task with a deadline\n"
            + "3. event_{description}_/at_{location} to create a task with a location\n"
            + "4. list to list all tasks out \n"
            + "5. delete_{taskId} to remove a particular task from the list\n"
            + "6. done_{taskId} to set a particular task to complete\n";

        return Optional.of(helpString);


    }
}
