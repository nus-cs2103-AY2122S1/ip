package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that will delete a Task from the TaskList when executed.
 *
 * @author ruiquan
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand given an index.
     * @param index the index of the Task to be deleted
     */
    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Executes the DeleteCommand and deletes the Task at the specified index
     * from the TaskList
     * @param tasks the collection of tasks
     * @param ui the user interface that handles input and output
     * @param storage the storage manager that deals with loading from and
     *               saving into a file
     * @throws DukeException if the file that act as storage can not be found
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index <= 0 || index > tasks.size()) {
            throw new DukeException("Looks like there is no such task to be deleted");
        }
        Task task = tasks.deleteTask(index);
        storage.save(tasks);
        int len = tasks.size();
        String message = String.format("Noted. I've removed this task:\n  %s\nNow you have %d %s in the list.",
                task.toString(),
                len,
                len <= 1 ? "task" : "tasks");
        ui.reply(message);
    }
}
