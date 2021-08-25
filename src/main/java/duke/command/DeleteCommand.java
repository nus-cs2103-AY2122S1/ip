package duke.command;

import duke.DukeException;
import duke.FileManager;
import duke.Tasklist;
import duke.Ui;
import duke.task.Task;

/**
 * A command which aims to delete a task.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Makes a DeleteCommand that deletes the task that was inputted.
     *
     * @param index the index of the task to be deleted.
     */
    public DeleteCommand(int index) {
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
    public void execute(Tasklist tasks, Ui ui, FileManager fileManager) throws DukeException {
        Task removedTask = tasks.getTask(this.index);
        tasks.delete(this.index);
        ui.deleteTask(removedTask);
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
