package duke.commands;

import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * The command indicating that the user wants to list all the tasks within the
 * task list.
 */
public class ListAllCommand extends Command {

    /**
     * Performs the necessary method invoking to print all the tasks within the task list.
     *
     * @param tasks the full task list containing all the tasks.
     * @param ui the ui instance.
     * @param storage the storage instance.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGettingAllTaskItemsInteraction(tasks);
    }
}
