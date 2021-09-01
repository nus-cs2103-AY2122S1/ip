package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

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
     * @param ui The user interface that handles input and output.
     * @param storage The storage manager that deals with loading from and
     *               saving into a file.
     * @throws DukeException If the file that act as storage can not be found.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);
        storage.save(tasks);
        int len = tasks.size();
        String message = String.format("Got it. I've added this task:\n  %s\nNow you have %d %s in the list.",
                task.toString(),
                len,
                len <= 1 ? "task" : "tasks");
        return message;
    }
}
