package ligma.command;

import ligma.Storage;
import ligma.TaskList;
import ligma.Ui;

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
    public void execute(TaskList tasks, Storage storage) {
        Ui.sayGoodbye();
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
