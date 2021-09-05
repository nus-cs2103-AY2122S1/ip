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
     * Constructs a DoneCommand given an index.
     *
     * @param index The index of the Task to be marked as done.
     */
    public DoneCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Executes the DoneCommand and mark the Task at the specified index
     * in the TaskList as done.
     *
     * @param tasks The collection of tasks.
     * @param ui The user interface that handles input and output.
     * @param storage The storage manager that deals with loading from and
     *               saving into a file.
     * @throws DukeException If the file that act as storage can not be found.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index <= 0 || index > tasks.size()) {
            throw new DukeException("Looks like there is no such task to be marked as done");
        }

        assert index <= tasks.size() && index > 0
                : "index should neither be negative nor bigger than the size of task list";
        Task task = tasks.markTaskAsDone(index);
        assert task.getStatusIcon().equals("X") : "The status of the task should be done";
        storage.save(tasks);

        String message = String.format("Nice! I've marked this task as done:\n  %s", task);
        return message;
    }
}
