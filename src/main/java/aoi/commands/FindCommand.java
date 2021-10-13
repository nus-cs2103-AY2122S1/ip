package aoi.commands;

import java.util.Arrays;

import aoi.data.TaskList;
import aoi.exceptions.AoiException;
import aoi.storage.Storage;

/**
 * Encapsulates the command responsible for deleting a task.
 *
 * @author Owen Tan
 * @version aoi.Aoi v1.2
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for FindCommand
     *
     * @param tokens User input.
     * @throws AoiException
     */
    public FindCommand(String[] tokens) throws AoiException {
        if (tokens.length <= 1) {
            throw new AoiException("Missing query keyword");
        }
        keyword = String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length));
    }

    /**
     * Searches for tasks that match the query keyword in TaskList.
     *
     * @param tasks TaskList associated to Aoi.
     * @param storage Storage associated to Aoi.
     * @return A string that contains the matching tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.printMatchingTasks(keyword);
    }
}
