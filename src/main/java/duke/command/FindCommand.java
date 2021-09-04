package duke.command;

import duke.Storage;
import duke.UI;
import duke.error.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Represents a command which finds tasks given a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand object.
     *
     * @param keyword Keyword used in searching the tasks.
     */
    public FindCommand(String keyword) {
        isExit = false;
        this.keyword = keyword;
    }

    /**
     * Prints tasks that contain keyword.
     *
     * @param tasks List of tasks.
     * @param ui UI object.
     * @param storage Storage object.
     * @throws DukeException If something goes wrong while saving.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.showFind();
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().contains(keyword)) {
                System.out.printf("%d.%s\n", i + 1, task);
            }
        }
        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new DukeException("OOPS!! something went wrong while trying to update tasks");
        }
    }
}
