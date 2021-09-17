package duke.commands;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;



public abstract class Command {
    /**
     * Indicates if the command is an exit command.
     */
    private boolean isExit = false;

    public boolean getExitStatus() {
        return this.isExit;
    }

    public void setExitStatus() {
        this.isExit = true;
    }

    /**
     * Executes the user's command.
     *
     * @param tasks Current TaskList.
     * @param storage Storage object which updates the local file.
     * @throws DukeException when user input is incorrect.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;
}
