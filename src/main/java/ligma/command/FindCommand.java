package ligma.command;

import ligma.Storage;
import ligma.TaskList;
import ligma.Ui;
import ligma.task.Task;

import java.util.ArrayList;
import java.util.Arrays;

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
    public void execute(TaskList tasks, Storage storage) {
        Task[] results = tasks.find(target);
        Ui.printFoundTasks(results);
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
