package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates the done command.
 */
public class DoneCommand implements Command {
    private int idx;

    /**
     * Constructor for a DoneCommand instance.
     *
     * @param idx Index at which the task to be marked as done is stored in user's task list.
     */
    public DoneCommand(int idx) {
        super();
        this.idx = idx;
    }

    /**
     * Marks the task at the given index in the task list as done.
     *
     * @param tasks User's list of tasks.
     * @param ui Duke's UI.
     * @throws DukeException For invalid inputs.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        if (idx >= tasks.getSize()) {
            throw new DukeException("☹ OOPS!!! That task doesn't exist.");
        }
        Task t = tasks.get(idx);
        t.markAsDone();
        System.out.println(("Nice! I've marked this task as done: \n\t" + t));
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
