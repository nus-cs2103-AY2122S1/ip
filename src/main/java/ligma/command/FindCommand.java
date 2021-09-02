package ligma.command;

import ligma.Storage;
import ligma.TaskList;
import ligma.ui.Ui;
import ligma.task.Task;

/**
 * This class represents a command to find
 * tasks containing a specific target string.
 */
public class FindCommand implements Command {
    private String target;

    public FindCommand(String target) {
        this.target = target;
    }

    /**
     * Displays tasks with specified string on UI.
     *
     * @param tasks     all tasks belonging to current Ligma program
     * @param storage   storage of current Ligma program
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task[] results = tasks.find(target);
        return Ui.getMatches(results);
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
