package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskHandler;
import duke.util.Ui;

/**
 * This class encapsulates the done command.
 *
 * @author Teo Sin Yee
 */
public class DoneCommand extends Command {
    private final int taskId;

    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Adds the task to the task list.
     * Writes task data to storage file.
     *
     * @param taskHandler TaskHandler of Duke.
     * @param storage Storage for Duke.
     * @param ui User interface.
     * @throws DukeException If there is error adding the task or saving data.
     */
    @Override
    public void execute(TaskHandler taskHandler, Storage storage, Ui ui) throws DukeException {
        taskHandler.markTaskAsDone(taskId);
        taskHandler.updateData();
    }
}