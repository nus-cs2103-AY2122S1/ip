package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a command for Duke bot.
 *
 * @author Li Ming Gao Rickie
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param taskList the list of user tasks.
     * @param input the user's input.
     * @param storage the storage which holds all the user's data.
     * @return a string that states the outcome of the execution of the command.
     * @throws DukeException if input is invalid.
     */
    public abstract String execute(TaskList taskList, String input, Storage storage) throws DukeException;

}
