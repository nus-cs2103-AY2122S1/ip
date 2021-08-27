package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.Ui;

import java.io.IOException;

/**
 * DeleteCommand is a duke.command which deletes a specific Task from the TaskList.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class DeleteCommand extends Command {

    /**
     * Constructor.
     *
     * @param description it should contain index of the duke.task to be deleted
     */
    public DeleteCommand(String description) {
        super(description);
    }

    /**
     * Deletes the duke.task from the duke.task list.
     *
     * @param tasks   the duke.task list
     * @param ui      the ui
     * @param storage the storage for the saved duke.task list
     * @throws DukeException if the index of the duke.task given is not within the last or if the data file
     *                       is missing / corrupted
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.valueOf(description);
            if (index > tasks.count()) {
                throw new IllegalArgumentException();
            }
            tasks.delete(index - 1);
            storage.delete(index - 1);
        } catch (IllegalArgumentException e) {
            throw new DukeException("There is no such duke.task in existence.");
        } catch (IOException e) {
            throw new DukeException("There is an error in deleting from your saved data.");
        }
    }
}
