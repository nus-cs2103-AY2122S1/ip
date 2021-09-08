package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates the delete command.
 */
public class DeleteCommand implements Command {
    private int idx;

    /**
     * Constructor for a DeleteCommand instance.
     *
     * @param idx Index at which the task to be deleted is stored in user's task list.
     */
    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Deletes the task at a given index from the given task list.
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
        String response = "Noted. I've removed this task: \n\t" + tasks.get(idx)
                + "\nNow you have " + ui.formatNumTasks(tasks.getSize() - 1) + " in the list.";
        tasks.remove(idx);
        return response;
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
