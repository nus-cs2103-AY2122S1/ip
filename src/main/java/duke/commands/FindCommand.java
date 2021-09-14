package duke.commands;

import java.util.HashMap;

import duke.tasks.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * The command indicating that the user wants to find all the tasks in the task list
 * that contains the particular input keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Searches all the existing tasks for those that contain the particular keyword and returns the output.
     *
     * @param tasks the full task list containing all the tasks.
     * @param ui the ui instance.
     * @param storage the storage instance.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        HashMap<String, Task> matchingTasks = tasks.getMatchingTasks(this.keyword);
        ui.showMessagePrintingAllMatchingTasks(matchingTasks, tasks);
    }
}
