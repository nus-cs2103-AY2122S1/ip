package daisy.command;

import daisy.DaisyException;
import daisy.task.Storage;
import daisy.task.TaskList;

/**
 * Abstract Command class that executes the command of the user.
 */
public abstract class Command {

    /**
     * Executes itself.
     *
     * @param taskList The TaskList of Daisy.
     * @param storage  The Storage of Daisy.
     * @return Response string.
     * @throws DaisyException If error occur during execution.
     */
    public abstract String execute(TaskList taskList, Storage storage) throws DaisyException;
}
