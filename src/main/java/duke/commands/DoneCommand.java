package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that will mark a Task as done when executed.
 *
 * @author ruiquan
 */
public class DoneCommand extends Command {
    private final int index;

    /**
     * Constucts a DoneCommand given an index.
     * @param index the index of the Task to be marked as done
     */
    public DoneCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Executes the DoneCommand and mark the Task at the specified index
     * in the TaskList as done.
     * @param tasks the collection of tasks
     * @param ui the user interface that handles input and output
     * @param storage the storage manager that deals with loading from and
     *               saving into a file
     * @throws DukeException if the file that act as storage can not be found
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index <= 0 || index > tasks.size()) {
            throw new DukeException("Looks like there is no such task to be marked as done");
        }
        Task task = tasks.markTaskAsDone(index);
        storage.save(tasks);
        String message = String.format("Nice! I've marked this task as done:\n  %s", task);
        ui.reply(message);
    }
}
