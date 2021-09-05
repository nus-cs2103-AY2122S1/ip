package duke.commands;

import duke.DukeException;
import duke.PersistentStorage;
import duke.Response;
import duke.Tasklist;
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
     * @param response The UI associated with the Duke instance.
     * @param storage The PersistentStorage associated with the Duke instance.
     * @return A CommandResult detailing the result of deleting a Task.
     * @throws DukeException if the provided target index is not in range.
     */
    public CommandResult executeCommand(Tasklist taskList, Response response, PersistentStorage storage)
            throws DukeException {
        // Check for valid task number provided
        if (this.target < 1 || this.target > taskList.getTotalTasks()) {
            throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
        }

        Task removed = taskList.deleteTask(target);
        return new CommandResult(response.showRemovedTask(taskList, removed));
    }
}
