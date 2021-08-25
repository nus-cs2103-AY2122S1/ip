package duke.commands;

import duke.Tasklist;
import duke.DukeException;
import duke.UI;
import duke.PersistentStorage;
import duke.tasks.Task;

/**
 * Class encapsulating a "done" command from the user
 */
public class DoneCommand extends Command{

    /** The integer index of the target task to be completed (as in the UI) */
    private int target;

    /**
     * A constructor for a DoneCommand
     *
     * @param target The integer index of the target task to be completed (as in the UI)
     */
    public DoneCommand(int target) {
        this.target = target;
    }

    /**
     * Executes the done command by marking the specified task as complete and displaying
     * the completed task.
     *
     * @param taskList The Tasklist associated with the Duke instance.
     * @param ui The UI associated with the Duke instance.
     * @param storage The PersistentStorage associated with the Duke instance.
     * @throws DukeException if the provided target index is not in range.
     */
    public void executeCommand(Tasklist taskList, UI ui, PersistentStorage storage) throws DukeException {
        // Validate target index
        if (this.target > taskList.getTotalTasks() || this.target < 1) {
            throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
        }

        Task completed = taskList.markAsDone(target);
        ui.showCompletedTask(completed);
    }
}
