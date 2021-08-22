package main.java;

import java.io.IOException;

/**
 * Delete is a command which deletes a specific Task from the TaskList.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S2
 */
public class Delete extends Command {

    /**
     * Constructor.
     *
     * @param description it should contain index of the task to be deleted
     */
    Delete(String description) {
        super(description);
    }

    /**
     * Deletes the task from the task list.
     *
     * @param tasks   the task list
     * @param ui      the ui
     * @param storage the storage for the saved task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.valueOf(description);
            if (index > tasks.count()) {
                throw new IllegalArgumentException();
            }
            tasks.delete(index - 1);
            storage.delete(index - 1);
        } catch (IllegalArgumentException e) {
            throw new DukeException("There is no such task in existence.");
        } catch (IOException e) {
            throw new DukeException("There is an error in deleting from your saved data.");
        }
    }
}
