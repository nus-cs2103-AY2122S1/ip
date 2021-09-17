package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * The RestoreCommand is given when the user wants to revert to a previously saved TaskList.
 */
public class RestoreCommand extends Command {

    public static final String COMMAND_WORD = "restore";
    private static final String SUCCESS_MSG = "This task list was restored:\n";

    public RestoreCommand() {

    }

    /**
     * Restores the TaskList to a previously saved one.
     * Also saves the restored TaskList in taskList.txt.
     *
     * @param tasks the given TaskList.
     * @param storage the given Storage.
     * @return the string for the Ui to print.
     * @throws DukeException when something goes wrong in the saving process.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        tasks.restore();
        storage.save(tasks);
        return SUCCESS_MSG + tasks.listTaskArr();
    }
}
