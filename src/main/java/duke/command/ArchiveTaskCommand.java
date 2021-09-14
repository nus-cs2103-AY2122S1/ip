package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
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
        if (idx >= tasks.getSize()) {
            throw new DukeException("OOPS!!! That task doesn't exist.");
        }
        assert tasks != null;
        Task t = tasks.get(idx);
        tasks.archive(idx);
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
