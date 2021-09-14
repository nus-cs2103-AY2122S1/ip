package duke.command;

import duke.Ui;

/**
 * Contains the method necessary for a Exit Command.
 *
 * @author Toh Wang Bin
 */
public class ExitCommand implements Command {

    /**
     * Executes the command unique to this Command class.
     *
     * @return A string representing the response to running this command.
     */
    public String execute() {
        return Ui.getEndMessage();
    }

}
