package duke.command;

import duke.DukeException;
import duke.Ui;

/**
 * The command to quit the program.
 */
public class ByeCommand extends Command {

    /**
     * Returns an output message after executing the bye command.
     * @return The output message of the command.
     * @throws DukeException Thrown when a duke exception happens.
     */
    @Override
    public String execute() throws DukeException {
        return Ui.getGoodByeMessage();
    }
}
