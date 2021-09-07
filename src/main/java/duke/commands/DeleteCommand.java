package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Represents a command that will delete a Task from the TaskList when executed.
 *
 * @author ruiquan
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand given an index.
     *
     * @param index The index of the Task to be deleted.
     */
    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    /**
     * Executes the DeleteCommand and deletes the Task at the specified index
     * from the TaskList.
     *
     * @param tasks The collection of tasks.
     * @param storage The storage manager that deals with loading from and
     *               saving into a file.
     * @throws DukeException If the file that act as storage can not be found.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (index <= 0 || index > tasks.size()) {
            throw new DukeException("Looks like there is no such task to be deleted");
        }
        assert index <= tasks.size() && index > 0
                : "index should neither be negative nor bigger than the size of task list";
        int before = tasks.size();
        Task task = tasks.deleteTask(index);
        int after = tasks.size();
        assert after == before - 1
                : "The size of task list after deleting a task should be one lesser than before";
        storage.save(tasks);

        int len = tasks.size();
        String message = String.format("Noted. I've removed this task:\n  %s\nNow you have %d %s in the list.",
                task.toString(),
                len,
                len <= 1 ? "task" : "tasks");
        return message;
    }
}
