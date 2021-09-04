package duke.command;

import duke.Storage;
import duke.UI;
import duke.error.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Represents a command which deletes a task from the list of tasks.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param index The index of the command to be deleted in the list.
     */
    public DeleteCommand(int index) {
        isExit = false;
        this.index = index;
    }

    /**
     * Deletes the command in index from the list of tasks.
     *
     * @param tasks List of tasks.
     * @param ui UI object.
     * @param storage Storage object.
     * @throws DukeException If the index is invalid, or something goes wrong while saving.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (index < 0 || index >= tasks.getSize()) {
            throw new DukeException("OOPS!! index is invalid");
        }
        Task task = tasks.deleteTask(index);
        ui.showDelete(task, tasks.getSize());

        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new DukeException("OOPS!! something went wrong while trying to update tasks");
        }
    }
}
