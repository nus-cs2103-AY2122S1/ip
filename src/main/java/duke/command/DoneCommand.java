package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.misc.TaskList;
import duke.misc.Ui;

/**
 * DoneCommand class to represent a command to mark a task as done.
 */
public class DoneCommand extends Command {
    private boolean isBye = false;
    private int idx;

    /**
     * Constructor for DoneCommand class.
     *
     * @param idx The task number of the task to be marked as done.
     */
    public DoneCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Executes the operation to mark the task as done.
     *
     * @param tl The current TaskList.
     * @return String to notify user of successful command execution.
     * @throws DukeException In case of Duke related errors.
     * @throws IOException In case of invalid file directory.
     */
    public String execute(TaskList tl) throws DukeException, IOException {
        return Ui.DONE_MSG + tl.completeTask(idx);
    }

    public boolean getIsBye() {
        return isBye;
    }
}
