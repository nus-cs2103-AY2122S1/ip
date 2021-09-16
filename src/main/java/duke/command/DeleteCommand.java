package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.misc.TaskList;
import duke.misc.Ui;

/**
 * DeleteCommand class to represent a command to delete a specific task.
 */
public class DeleteCommand extends Command {
    private boolean isBye = false;
    private int idx;

    /**
     * Constructor for DeleteCommand class.
     *
     * @param idx The task number of the task to be deleted.
     */
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the task deletion operation.
     *
     * @param tl The current TaskList.
     * @return String to notify user of successful command execution.
     * @throws DukeException In case of Duke related errors.
     * @throws IOException In case of invalid file directory.
     */
    public String execute(TaskList tl) throws DukeException, IOException {
        return Ui.DELETE_MSG + tl.deleteTask(idx);
    }

    public boolean getIsBye() {
        return isBye;
    }
}
