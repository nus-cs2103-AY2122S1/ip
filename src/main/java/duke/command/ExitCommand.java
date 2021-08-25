package duke.command;

import duke.util.DukeException;
import duke.util.Storage;
import duke.task.TaskList;
import duke.util.Ui;

/**
 * Set program state to exit.
 */
public class ExitCommand extends Command {

    /**
     * Indicates if the command is an exit command.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Save task list data to local storage
     *
     * @param storage storage instance initialised when duke is created.
     * @param taskList task list instance initialised when duke is created.
     * @param ui ui instance initialised when duke is created.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
            storage.writeToDisk(taskList.compileTasks());
    }
}
