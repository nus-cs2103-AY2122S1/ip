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
    private final int code;

    /**
     * The constructor for the ErrorCommand object.
     *
     * @param code The error code
     */
    public ErrorCommand(int code) {
        super(CommandType.ERROR, false);
        this.code = code;
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
            throw new DukeException(this.code);
        } catch (DukeException e) {
            return response.showError(e);
        }
    }
}
