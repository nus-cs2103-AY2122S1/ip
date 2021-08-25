package duke.commands;

import duke.Tasklist;
import duke.DukeException;
import duke.UI;
import duke.PersistentStorage;
import duke.tasks.Task;

/**
 * Class encapsulating a "delete" command from the user
 */
public class DeleteCommand extends Command {

    /** The integer index of the task to be deleted (as in the UI) */
    private int target;

    /**
     * A constructor for a DeleteCommand
     *
     * @param target The integer index of the task to be deleted (as in the UI)
     */
    public DeleteCommand(int target) {
        this.target = target;
    }

    /**
     * Executes the delete command by deleting the specified task and displaying the
     * deleted task.
     *
     * @param taskList The Tasklist associated with the Duke instance.
     * @param ui The UI associated with the Duke instance.
     * @param storage The PersistentStorage associated with the Duke instance.
     * @throws DukeException if the provided target index is not in range.
     */
    public void executeCommand(Tasklist taskList, UI ui, PersistentStorage storage) throws DukeException{
        // Check for valid task number provided
        if (this.target < 1 || this.target > taskList.getTotalTasks()) {
            throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
        }

        Task removed = taskList.deleteTask(target);
        ui.showRemovedTask(taskList, removed);
    }
}
