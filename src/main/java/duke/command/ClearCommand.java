package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

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
     * @throws DukeException when something goes wrong with the saving of the TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.clear();
        storage.save(tasks);
        ui.printMsg("All entries in the list of tasks have been removed. To undo, type restore");
    }
}
