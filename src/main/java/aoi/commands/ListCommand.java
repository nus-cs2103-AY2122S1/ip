package aoi.commands;

import aoi.data.TaskList;
import aoi.storage.Storage;

/**
 * Encapsulates the command for listing all tasks.
 *
 * @author Owen Tan
 * @version aoi.Aoi v1.2
 */
public class ListCommand extends Command {

    /**
     * Lists all tasks in TaskList.
     *
     * @param tasks TaskList associated to Aoi.
     * @param storage Storage associated to Aoi.
     * @return A string containing all tasks in TaskList.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.printTaskList();
    }
}
