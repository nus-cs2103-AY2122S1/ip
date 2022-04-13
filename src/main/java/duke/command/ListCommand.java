package duke.command;

import duke.error.DukeException;
import duke.general.Storage;
import duke.general.Tasklist;
import duke.general.Ui;

/**
 * Command to print out the Tasklist to the user
 */
public class ListCommand extends Command {

    @Override
    public String execute(Tasklist tasks, Storage storage, Ui ui) throws DukeException {
        return ui.showList(tasks);
    }
}
