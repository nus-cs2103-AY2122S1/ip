package duke.commands;

import duke.DukeException;
import duke.tasks.Task;
import duke.util.PersistentStorage;
import duke.util.Response;
import duke.util.Tasklist;

/**
 * Class encapsulating a "done" command from the user
 */
public class DoneCommand extends Command {

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
     * @param response The UI associated with the Duke instance.
     * @param storage The PersistentStorage associated with the Duke instance.
     * @return A CommandResult detailing the result of marking a Task as complete.
     * @throws DukeException if the provided target index is not in range.
     */
    public CommandResult executeCommand(Tasklist taskList, Response response, PersistentStorage storage)
            throws DukeException {
        // Validate target index
        if (this.target > taskList.getTotalTasks() || this.target < 1) {
            throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
        }

        // Target index should be at least 1
        assert target >= 1 : "Target index for Done should be at least 1!";

        Task completed = taskList.markAsDone(target);
        storage.saveTasks(taskList);
        return new CommandResult(response.showCompletedTask(completed));
    }
}
