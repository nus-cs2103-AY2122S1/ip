package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.DukeOutOfBoundsException;

public class UnarchiveTaskCommand implements Command {
    private int idx;

    /**
     * Constructor for an UnarchiveTaskCommand instance.
     *
     * @param idx Index at which the task to be archived is stored in user's archive list.
     */
    public UnarchiveTaskCommand(int idx) {
        this.idx = idx;
    }

    /**
     * Removes the given task from the archive list and puts it back into the main list.
     *
     * @param taskList User's list of tasks.
     * @param ui Duke's UI.
     * @return The String representation of Duke's response.
     * @throws DukeException For invalid inputs.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        assert taskList != null;
        try {
            String response = "Noted. I've unarchived this task: \n\t" + taskList.getArchivedTask(idx)
                + "\nYou can now find it in your main list.";
            taskList.unarchive(idx);
            return response;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeOutOfBoundsException();
        }
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
