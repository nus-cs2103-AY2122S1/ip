package duke.command;

import duke.Storage;
import duke.UI;
import duke.error.DukeException;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Represents a command which exits the program.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand object.
     */
    public ExitCommand() {
        isExit = true;
    }

    /**
     * Saves the task before exiting the program.
     *
     * @param tasks List of tasks.
     * @param ui UI object.
     * @param storage Storage object.
     * @throws DukeException If something goes wrong while saving.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new DukeException("OOPS!! something went wrong while trying to save tasks");
        }
        ui.showExit();
    }
}
