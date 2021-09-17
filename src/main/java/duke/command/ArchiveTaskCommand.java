package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.DukeOutOfBoundsException;
import duke.task.Task;

/**
 * Encapsulates the archive command.
 */
public class ArchiveTaskCommand implements Command {
    private int idx;

    /**
     * Constructor for an ArchiveTaskCommand instance.
     *
     * @param idx Index of the to-be-archived task in the user's main task list.
     */
    public ArchiveTaskCommand(int idx) {
        super();
        this.idx = idx;
    }

    /**
     * Marks the task at the given index in the task list as done.
     *
     * @param tasks User's list of tasks.
     * @param ui Duke's UI.
     * @return The String representation of Duke's response.
     * @throws DukeException For invalid inputs.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) throws DukeException {
        assert tasks != null;
        Task t;
        try {
            t = tasks.get(idx);
            tasks.archive(idx);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeOutOfBoundsException();
        }
        return "Ok. I've archived the task: \n\t" + t;
    }

    /**
     * Indicates if the command is an exit command.
     *
     * @return If the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
