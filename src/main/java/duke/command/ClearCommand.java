package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * The ClearCommand is the Command given to clear the TaskList of its tasks.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    public ClearCommand() {

    }

    /**
     * Clears the given TaskList of its tasks.
     * Will also save the empty TaskList to taskList.txt.
     *
     * @param tasks the given TaskList.
     * @param ui the given Ui.
     * @param storage the given Storage.
     * @return the string for the Ui to print.
     * @throws DukeException when something goes wrong with the saving of the TaskList.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.clear();
        storage.save(tasks);
        return "All entries in the list of tasks have been removed. To undo, type restore";
    }
}
