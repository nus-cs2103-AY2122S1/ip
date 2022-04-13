package duke.command;

import java.io.IOException;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.utils.DukeException;

/**
 * DeleteCommand is a command which deletes a specific Task from the TaskList.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class DeleteCommand extends Command {

    /**
     * Constructor.
     *
     * @param description it should contain index of the task to be deleted
     */
    public DeleteCommand(String description) {
        super(description);
    }

    /**
     * Deletes the task from the task list.
     *
     * @param tasks   the task list
     * @param storage the storage for the saved task list
     * @throws DukeException if the index of the task given is not within the last or if the data file
     *                       is missing / corrupted
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            int index = Integer.valueOf(DESCRIPTION);
            if (index > tasks.count()) {
                throw new IllegalArgumentException();
            }
            storage.delete(index - 1);
            return tasks.delete(index - 1);
        } catch (IllegalArgumentException e) {
            throw new DukeException("There is no such task in existence.");
        } catch (IOException e) {
            throw new DukeException("There is an error in deleting from your saved data.");
        }
    }
}
