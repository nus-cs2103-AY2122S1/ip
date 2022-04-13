package duke.command;

import duke.Ui;

/**
 * Contains the methods necessary for a Help Command.
 *
 * @author Toh Wang Bin
 */
public class HelpCommand implements Command {

    /**
     * Executes the command unique to this Command class.
     *
     * @return A string representing the response to running this command.
     */
    public String execute() {
        return Ui.getHelpMessage();
    }

}
