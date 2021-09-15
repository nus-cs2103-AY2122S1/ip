package duke.task.command;

import duke.DukeException;
import duke.Ui;

/**
 * Class to implement the bye command.
 */
public class ByeCommand extends Command {

    /**
     * Executes the command.
     *
     * @return The output message of the command.
     */
    @Override
    public String execute() throws DukeException {
        return Ui.printGoodBye();
    }
}
