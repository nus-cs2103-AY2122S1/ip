package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.UI;
import duke.error.DukeException;
import duke.task.TaskList;

/**
 * Represents a command which exits the program.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand object.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Saves the task before exiting the program.
     *
     * @param tasks List of tasks.
     * @param ui UI object.
     * @param storage Storage object.
     * @return The execution result.
     * @throws DukeException If something goes wrong while saving.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new DukeException("OOPS!! something went wrong while trying to save tasks");
        }

        return ui.exitResponse();
    }
}
