package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.IndexOutOfBoundsException;


/**
 * Represents a Command that deletes a task from the TaskList.
 * A DeleteCommand contains an index representing the index of the task in the TaskList to be deleted.
 */
public class DeleteCommand extends Command {

    private int toDelete;

    /**
     * Creates a DeleteCommand Object.
     *
     * @param toDelete Index of task to be deleted in the TaskList
     */
    public DeleteCommand(int toDelete) {
        this.toDelete = toDelete;
    }

    /**
     * Deletes the task from TaskList.
     *
     * @param tasks TaskList to delete task from.
     * @param ui UI that reflects the changes made to the TaskList.
     * @param storage unused.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IndexOutOfBoundsException {
        if (toDelete >= tasks.getSize()) {
            throw new IndexOutOfBoundsException("Index does not exist!");
        }
        String output = ui.printDelete(tasks, toDelete);
        tasks.delete(toDelete);
        return output;
    }
}