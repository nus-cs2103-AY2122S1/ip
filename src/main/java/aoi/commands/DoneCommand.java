package aoi.commands;

import aoi.data.TaskList;
import aoi.exceptions.AoiException;
import aoi.storage.Storage;
import aoi.task.Task;
import aoi.ui.Ui;

/**
 * Encapsulates the command responsible for marking a task as completed.
 *
 * @author Owen Tan
 * @version aoi.Aoi v1.2
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * Constructor for DoneCommand.
     *
     * @param tokens Input from user.
     */
    public DoneCommand(String[] tokens) {
        index = Integer.parseInt(tokens[1]) - 1;
    }

    /**
     * Marks the associated Task as Done in TaskList.
     *
     * @param tasks TaskList associated to Aoi.
     * @param storage Storage associated to Aoi.
     * @return A string to show Task is completed.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws AoiException {
        Task task = tasks.get(index);
        tasks.complete(index);
        return Ui.showCompleteTaskMsg(task);
    }
}
