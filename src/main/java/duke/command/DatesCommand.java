package duke.command;

import duke.DukeException;
import duke.Ui;

/**
 * The command to get the available dates list.
 */
public class DatesCommand extends Command {

    /**
     * Returns an output message after executing the dates command.
     * @return The output message of the command.
     * @throws DukeException Thrown when a duke exception happens.
     */
    @Override
    public String execute() throws DukeException {
        return Ui.getAllAcceptedDates();
    }
}
