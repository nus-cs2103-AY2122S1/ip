package duke.command;

import duke.DukeException;
import duke.Ui;

/**
 * The command to get the available dates list.
 */
public class DatesCommand extends Command {

    @Override
    public String execute() throws DukeException {
        return Ui.getAllAcceptedDates();
    }
}
