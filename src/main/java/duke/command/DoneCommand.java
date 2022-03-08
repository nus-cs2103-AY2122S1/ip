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

    /**
     * Instantiates a new DoneCommand.
     *
     * @param taskId Index of task to be marked as done.
     */
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
     * @return Message to be shown to user.
     * @throws DukeException If there is error adding the task or saving data.
     */
    @Override
    public String execute(TaskHandler taskHandler, Storage storage, Ui ui) throws DukeException {
        assert taskId > 0 : "Invalid task index";
        return taskHandler.markTaskAsDone(taskId);
    }
}
