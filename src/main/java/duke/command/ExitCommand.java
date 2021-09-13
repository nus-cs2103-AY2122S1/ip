package duke.command;

import duke.task.TaskList;
import duke.util.DukeException;
import duke.util.Storage;

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
     * Saves task list data to local storage
     *
     * @param storage storage instance initialised when duke is created.
     * @param taskList task list instance initialised when duke is created.
     * @return message to confirm command execution.
     * @throws DukeException on error writing to disk.
     */
    @Override
    public String execute(Storage storage, TaskList taskList) throws DukeException {
        storage.writeToDisk(taskList.compileTasks());
        return "Existence is a pain to a Meeseeks Jerry...";
    }
}
