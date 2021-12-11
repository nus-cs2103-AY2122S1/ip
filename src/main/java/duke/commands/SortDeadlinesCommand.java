package duke.commands;

import java.util.ArrayList;
import java.util.Collections;

import duke.tasks.Deadlines;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * The command indicating that the user wants to see a list of all the deadlines in the
 * task list, but sorted in chronological order.
 */
public class SortDeadlinesCommand extends Command {

    /**
     * Gives the user the output containing all the deadlines inside the task list, with them sorted
     * in chronological order.
     *
     * @param tasks the full task list containing all the tasks.
     * @param ui the ui instance.
     * @param storage the storage instance.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Deadlines> allDeadlines = tasks.getAllDeadlines();
        Collections.sort(allDeadlines);
        ui.showGettingAllSortedDeadlinesInteraction(allDeadlines, tasks);
    }
}
