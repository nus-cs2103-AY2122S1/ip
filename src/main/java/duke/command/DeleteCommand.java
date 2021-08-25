package duke.command;

import duke.DukeException;
import duke.FileManager;
import duke.Tasklist;
import duke.UI;
import duke.task.Task;

/**
 * A command which aims to delete a task.
 */
public class DeleteCommand extends Command {
    private final Task task;
    private final int index;

    /**
     * Makes a DeleteCommand that deletes the task that was inputted.
     *
     * @param task the task to be deleted.
     * @param index the index of the task to be deleted.
     */
    public DeleteCommand(Task task, int index) {
        this.task = task;
        this.index = index;
    }

    /**
     * Deletes the task from current list of task.
     *
     * @param tasks current list of tasks.
     * @param ui the ui to interact with the user.
     * @param fileManager the filemanger that manages the storage of duke.
     * @throws DukeException if index is out of bounds or if task is missing.
     */
    @Override
    public void execute(Tasklist tasks, UI ui, FileManager fileManager) throws DukeException {
        tasks.delete(this.index);
        ui.deleteTask(this.task);
        fileManager.updateTaskList(tasks, ui);
    }

    /**
     * Returns if the function is a exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
