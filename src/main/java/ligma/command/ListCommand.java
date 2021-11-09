package ligma.command;

import ligma.Storage;
import ligma.TaskList;
import ligma.ui.Ui;

/**
 * This class represents a command to list all the tasks.
 */
public class ListCommand implements Command {
    /**
     * Displays all tasks on UI.
     *
     * @param tasks     all tasks belonging to current Ligma program
     * @param storage   storage of current Ligma program
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return Ui.getStringTaskList(tasks);
    }

    /**
     * Returns true if command is an exit command.
     *
     * @return true if command is an exit command, false otherwise
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
