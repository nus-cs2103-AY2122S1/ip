package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

import java.io.IOException;

/**
 * This abstract Command class represents an executable command.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks The task list.
     * @param storage The storage system of the application.
     * @throws DukeException If the command cannot be executed.
     * @throws IOException If the data cannot be saved in the file.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException, IOException;
}
