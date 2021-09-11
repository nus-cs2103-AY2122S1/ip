package duke.command;

import duke.DukeException;
import duke.Ui;

/**
 * The command to get the help list.
 */
public class HelpCommand extends Command {

    @Override
    public String execute() throws DukeException {
        return Ui.getHelpMenu();
    }
}
