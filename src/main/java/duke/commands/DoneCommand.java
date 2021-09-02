package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * The DoneCommand handles when a task is to be marked as done.
 */
public class DoneCommand extends Command {

    private final int taskIdx;

    /**
     * Constructs a DoneCommand Object with the given index.
     * @param idx Index of task to be marked as done.
     * @throws DukeException if invalid command entered.
     */
    public DoneCommand(String idx) throws DukeException {
        try {
            taskIdx = Integer.parseInt(idx.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid Command. 'done' must be followed by a task number");
        }
    }

    /**
     * Executes the marking of the Task in the TaskList at the given index as done.
     *
     * @param taskList The current TaskList being used.
     * @param ui The current Ui being used.
     * @param storage The current Storage being used.
     * @throws DukeException if invalid task number entered.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskIdx >= taskList.size()) {
            throw new DukeException("Invalid task number entered.");
        }
        Task task = taskList.getTask(taskIdx);
        task.markAsDone();
        storage.write(taskList);
        return ui.showTaskDone(task);
    }

}
