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
    private final int fromIndex;
    private final int toIndex;

    /**
     * Constructs a DeleteCommand that will remove the task at
     * the given index when executed.
     *
     * @param index Index of the task to be removed.
     */
    public DeleteCommand(int index) {
        super(false);
        fromIndex = index;
        toIndex = index;
    }

    /**
     * Constructs a DeleteCommand that will remove all the tasks
     * whose index is between fromIndex, inclusive and toIndex, exclusive when executed.
     *
     * @param fromIndex Index of the first task to be removed.
     * @param toIndex Index after the last task to be removed.
     */
    public DeleteCommand(int fromIndex, int toIndex) {
        super(false);
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }

    /**
     * Executes the DeleteCommand accordingly.
     *
     * @param tasks The collection of tasks.
     * @param storage The storage manager that deals with loading from and
     *               saving into a file.
     * @return The message representing the response.
     * @throws DukeException If the file that act as storage can not be found.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (fromIndex < 0 || toIndex > tasks.size() || toIndex < fromIndex) {
            throw new DukeException("Looks like there is something wrong with the range given");
        }

        String message;
        if (fromIndex == toIndex) {
            int before = tasks.size();
            Task deletedTask = tasks.deleteTask(fromIndex);
            int after = tasks.size();
            assert after == before - 1
                    : "The size of task list after deleting a task should be one lesser than before";
            int len = tasks.size();
            message = String.format("Noted. I've removed this task:\n  %s\nNow you have %d %s in the list.",
                    deletedTask.toString(),
                    len,
                    len <= 1 ? "task" : "tasks");
        } else {
            int before = tasks.size();
            TaskList deletedTasks = tasks.deleteRange(fromIndex, toIndex);
            int after = tasks.size();
            assert after == before - (toIndex - fromIndex)
                    : "The size of the task list after deleting (toIndex - fromIndex) number of tasks should be"
                    + "(toIndex - fromIndex) number of tasks lesser than before";
            message = String.format("Noted. I've removed these tasks:\n%s\nNow you have %d %s in the list.",
                    deletedTasks.toString(),
                    tasks.size(),
                    tasks.size() <= 1 ? "task" : "tasks");
        }
        storage.save(tasks);
        return message;
    }
}
