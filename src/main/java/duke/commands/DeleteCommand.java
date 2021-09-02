package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * The DeleteCommand handles when a task is to be deleted.
 */
public class DeleteCommand extends Command {

    private final int taskIdx;

    /**
     * Constructs a DeleteCommand Object.
     *
     * @param idx Index of task to be deleted.
     * @throws DukeException If there is no task at given index.
     */
    public DeleteCommand(String idx) throws DukeException {
        try {
            taskIdx = Integer.parseInt(idx.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid Command. 'delete' must be followed by a task number");
        }
    }

    /**
     * Executes the deletion of the Task in the TaskList at the given index.
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
        taskList.removeTask(taskIdx);
        storage.write(taskList);
        return ui.showTaskDeleted(task, taskList);
    }

}
