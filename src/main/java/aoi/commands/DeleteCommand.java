package aoi.commands;

import aoi.data.TaskList;
import aoi.exceptions.AoiException;
import aoi.storage.Storage;
import aoi.task.Task;
import aoi.ui.Ui;

/**
 * Encapsulates the command responsible for deleting a task.
 *
 * @author Owen Tan
 * @version aoi.Aoi v1.2
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor for DeleteCommand.
     *
     * @param tokens Input from user.
     */
    public DeleteCommand(String[] tokens) {
        index = Integer.parseInt(tokens[1]) - 1;
    }

    /**
     * Deletes the associated Task in TaskList.
     *
     * @param tasks TaskList associated to Aoi.
     * @param storage Storage associated to Aoi.
     * @return A string to show that task has been deleted.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws AoiException {
        Task task = tasks.get(index);
        tasks.delete(index);
        return Ui.showDeleteTaskMsg(task) + Ui.showListCountMsg(tasks);
    }
}
