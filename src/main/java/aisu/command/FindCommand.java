package aisu.command;

import java.util.List;

import aisu.storage.Storage;
import aisu.task.Task;
import aisu.tasklist.TaskList;
import aisu.ui.Ui;

/**
 * Command to find tasks with a keyword from the tasklist.
 *
 * @author Liaw Xin Yan
 */
public class FindCommand extends Command {
    private final String input;

    /** {@inheritDoc} */
    public FindCommand(String input) {
        this.input = input.trim();
    }

    /**
     * Finds tasks with a keyword and displays them to user.
     *
     * @param tasklist TaskList used in Aisu.
     * @param storage  Storage used in Aisu.
     * @param ui       User interface used in Aisu.
     */
    @Override
    public void execute(TaskList tasklist, Storage storage, Ui ui) {
        List<Task> result = tasklist.findTasksWith(this.input);
        TaskList resultList = new TaskList(result);
        this.uiText = ui.formatText("Here's what I found:", resultList.toString());
    }

    /** {@inheritDoc} */
    @Override
    public boolean isExit() {
        return false;
    }
}
