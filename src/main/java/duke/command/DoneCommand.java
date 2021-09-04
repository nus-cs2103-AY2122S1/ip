package duke.command;

import duke.Storage;
import duke.UI;
import duke.error.DukeException;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Represents a command which marks a task as done.
 */
public class DoneCommand extends Command {
    private final int index;

    /**
     * Constructs a DoneCommand.
     *
     * @param index Index of the task to be marked as done.
     */
    public DoneCommand(int index) {
        isExit = false;
        this.index = index;
    }

    /**
     * Updates the ask in index and marks it as done.
     *
     * @param tasks List of tasks.
     * @param ui UI object.
     * @param storage Storage object.
     * @throws DukeException If task index is invalid, or something goes wrong while saving.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (index >= tasks.getSize()) {
            throw new DukeException("OOPS!! task index is invalid");
        }

        tasks.updateStatus(index);
        ui.showDone(tasks.getTask(index));

        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new DukeException("OOPS!! something went wrong while trying to update tasks");
        }
    }
}
