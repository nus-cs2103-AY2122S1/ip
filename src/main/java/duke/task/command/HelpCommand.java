package duke.task.command;

import duke.Ui;

/**
 * The command to display the help menu.
 */
public class HelpCommand extends Command {

    /**
     * Executes the help command.
     *
     * @return The output message of the command.
     */
    @Override
    public String execute() {
        return Ui.getHelpMenu();
    }
}
