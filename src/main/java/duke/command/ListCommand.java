package duke.command;

import duke.Storage;
import duke.UI;
import duke.error.DukeException;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Represents a command which lists the current tasks in the list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand object.
     */
    public ListCommand() {
        isExit = false;
    }

    /**
     * Prints the current tasks in the list.
     *
     * @param tasks List of tasks.
     * @param ui UI object.
     * @param storage Storage object.
     * @throws DukeException If something goes wrong while saving.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.showList();
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.printf("%d.%s\n", i + 1, tasks.getTask(i));
        }

        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new DukeException("OOPS!! something went wrong while trying to update tasks");
        }
    }
}
