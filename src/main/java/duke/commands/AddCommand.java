package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Represents a command that will add Task into the TaskList when executed.
 *
 * @author ruiquan
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs a AddCommand given a Task.
     *
     * @param task The task that will be added into the TaskList.
     */
    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    /**
     * Executes the AddCommand and add the Task into the TaskList.
     *
     * @param tasks The collection of tasks.
     * @param storage The storage manager that deals with loading from and
     *               saving into a file.
     * @return The message representing the response.
     * @throws DukeException If the file that act as storage can not be found.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        int before = tasks.size();
        tasks.addTask(task);
        int after = tasks.size();
        assert after == before + 1 : "The size of the task list after adding a task should be one more than before";
        storage.save(tasks);

        int len = tasks.size();
        String message = String.format("Got it. I've added this task:\n  %s\nNow you have %d %s in the list.",
                task.toString(),
                len,
                len <= 1 ? "task" : "tasks");
        return message;
    }
}
