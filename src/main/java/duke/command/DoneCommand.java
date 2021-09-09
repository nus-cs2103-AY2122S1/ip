package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a Command to mark a Task as 'done' on the TaskList.
 */
public class DoneCommand extends Command {

    private int index;

    /**
     * The constructor for a DoneCommand object.
     *
     * @param index The given index of the Task to be marked as 'done'.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the Command to mark a Task as 'done'.
     *
     * @param tasks The given TaskList to be updated.
     * @param ui The given Ui to print messages to the user.
     * @param storage The given Storage to save changes to.
     * @throws DukeException When the given index does not exist.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index > tasks.getSize()) {
            throw new DukeException("This entry does not exist!");
        }

        tasks.doneTask(index);
        ui.printTemplate(" Nice! I've marked this task as done:\n" + "  " + tasks.getTask(index));
        storage.update(tasks);
    }
}
