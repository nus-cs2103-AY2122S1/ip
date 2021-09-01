package duke.commands;

import duke.errors.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Response;

/**
 * The ErrorCommand class extends the Command class and is the Command that * edits a Task to the
 * TaskList.
 */
public class ErrorCommand extends Command {

    /** The error code. */
    private final DukeException dukeException;

    /**
     * The constructor for the ErrorCommand object.
     *
     * @param dukeException The error code
     */
    public ErrorCommand(DukeException dukeException) {
        super(CommandType.ERROR, false);
        this.dukeException = dukeException;
    }

    /**
     * The execute command that executes the necessary actions when an
     * error is issued.
     *
     * @param tasks The TaskList to be added to
     * @param response The Ui object to interact with the user
     * @param storage The Storage object that stores the TaskList on the Local Machine
     */
    public String execute(TaskList tasks, Response response, Storage storage) {
        try {
            throw this.dukeException;
        } catch (DukeException e) {
            return response.showError(e);
        }
    }
}
