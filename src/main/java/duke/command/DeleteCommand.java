package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;

/**
 * Implements the logic for deleting tasks from the users list.
 */
public class DeleteCommand extends Command{
    private final int index;

    /**
     * Constructor called when deleting a task from the TaskList.
     * @param index Index of item to be deleted
     */
    public DeleteCommand (int index) {
        this.index = index;
    }

    /**
     * Deletes a task from the TaskList.
     * @param tasklist TaskList that contains all the users current tasks.
     * @param ui Ui object for interaction with user
     * @param store Storage object that handles save and load functionality.
     * @throws IndexOutOfBoundsException If user gives an index not corresponding to an item in the list.
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage store) throws IndexOutOfBoundsException {
        Task removed = tasklist.delete(index);
        ui.notifySuccessfulDelete(tasklist, removed);
    }
}
