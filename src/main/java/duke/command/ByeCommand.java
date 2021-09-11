package duke.command;

import duke.DukeException;
import duke.Ui;

/**
 * The command to quit the program.
 */
public class ByeCommand extends Command {

    @Override
    public String execute() throws DukeException {
        return Ui.getGoodByeMessage();
    }
}
