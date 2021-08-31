package duke.command;

import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Abstract class comprising information required to change the state of duke when executed.
 */
public abstract class Command {
    /**
     * Abstract method that performs a series of action to change the state of Duke
     * and returns a message depending on the command type.
     *
     * @param storage storage instance initialised when duke is created.
     * @param taskList task list instance initialised when duke is created.
     * @param ui ui instance initialised when duke is created.
     * @throws DukeException command cannot be executed successfully.
     * @return String to provide details on the execution process.
     */
    public abstract String execute(Storage storage, TaskList taskList, Ui ui) throws DukeException;

    /**
     * Verify if the user has instructed the duke to exit.
     *
     * @return is the command an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
