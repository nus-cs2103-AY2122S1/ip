package ligma.command;

import ligma.Storage;
import ligma.TaskList;
import ligma.ui.Ui;

/**
 * This class represents a command to exit the Ligma program.
 */
public class ExitCommand implements Command {
    /**
     * Updates UI to bid user farewell.
     *
     * @param tasks     all tasks belonging to current Ligma program
     * @param storage   storage of current Ligma program
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return Ui.getFarewell();
    }

    /**
     * Returns true if command is an exit command.
     *
     * @return true if command is an exit command, false otherwise
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
